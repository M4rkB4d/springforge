#!/usr/bin/env bash
#
# SpringForge Progress Checker
# Usage: ./scripts/check-progress.sh <module-id>
# Example: ./scripts/check-progress.sh t1-02
#

set -euo pipefail

MODULE_ID="${1:-}"

if [ -z "$MODULE_ID" ]; then
    echo ""
    echo "  SpringForge Progress Checker"
    echo "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━"
    echo "  Usage: ./scripts/check-progress.sh <module-id>"
    echo "  Example: ./scripts/check-progress.sh t1-02"
    echo ""
    echo "  Available modules:"
    for dir in exercises/*/; do
        if [ -d "$dir" ]; then
            name=$(basename "$dir")
            echo "    ${name}"
        fi
    done
    echo ""
    exit 1
fi

# Convert module ID to package name (t1-02 -> t1_02)
PACKAGE_NAME=$(echo "$MODULE_ID" | tr '-' '_')

# Find the exercise directory
EXERCISE_DIR=""
for dir in exercises/*"${MODULE_ID}"*/; do
    if [ -d "$dir" ]; then
        EXERCISE_DIR="$dir"
        break
    fi
done

if [ -z "$EXERCISE_DIR" ]; then
    echo "Error: No exercise directory found for module '${MODULE_ID}'"
    exit 1
fi

MODULE_TITLE=$(basename "$EXERCISE_DIR" | sed 's/^[a-z0-9]*-[a-z0-9]*-//' | tr '-' ' ' | sed 's/\b\(.\)/\u\1/g')

echo ""
echo "  SpringForge Progress: ${MODULE_ID^^} ${MODULE_TITLE}"
echo "  ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━"
echo ""

# Run tests and capture output
TEST_OUTPUT=$(./mvnw test -Dtest="dev/springforge/${PACKAGE_NAME}/**" -pl . --no-transfer-progress 2>&1 || true)

# Parse test results per test class
TOTAL_PASS=0
TOTAL_FAIL=0
TOTAL_SKIP=0
TOTAL_EXERCISES=0

# Find all test files for this module
for test_file in "src/test/java/dev/springforge/${PACKAGE_NAME}"/Ex*.java; do
    if [ ! -f "$test_file" ]; then
        continue
    fi

    TOTAL_EXERCISES=$((TOTAL_EXERCISES + 1))
    CLASS_NAME=$(basename "$test_file" .java)
    FULL_CLASS="dev.springforge.${PACKAGE_NAME}.${CLASS_NAME}"

    # Count tests in this class
    TEST_COUNT=$(grep -c '@Test' "$test_file" 2>/dev/null || echo "0")

    # Check if this class appears in error/failure output (Maven uses class name in detail lines)
    if echo "$TEST_OUTPUT" | grep -q "${CLASS_NAME}.*ERROR\!\|${CLASS_NAME}.*FAILURE\!"; then
        # Tests ran but had errors or failures
        ERROR_COUNT=$(echo "$TEST_OUTPUT" | grep -c "${CLASS_NAME}\..*<<<" || echo "0")
        PASS_COUNT=$((TEST_COUNT - ERROR_COUNT))
        if [ "$PASS_COUNT" -lt 0 ]; then PASS_COUNT=0; fi
        STATUS="FAIL"
        TOTAL_FAIL=$((TOTAL_FAIL + 1))
    elif echo "$TEST_OUTPUT" | grep -qi "compilation failure\|cannot find symbol\|does not exist"; then
        STATUS="SKIP"
        PASS_COUNT=0
        TOTAL_SKIP=$((TOTAL_SKIP + 1))
    elif echo "$TEST_OUTPUT" | grep -q "BUILD SUCCESS"; then
        # If build succeeded and no errors for this class, it passed
        PASS_COUNT=$TEST_COUNT
        STATUS="PASS"
        TOTAL_PASS=$((TOTAL_PASS + 1))
    else
        STATUS="SKIP"
        PASS_COUNT=0
        TOTAL_SKIP=$((TOTAL_SKIP + 1))
    fi

    # Format output
    DISPLAY_NAME=$(echo "$CLASS_NAME" | sed 's/_/ /g')
    DOTS=$(printf '.%.0s' $(seq 1 $((40 - ${#DISPLAY_NAME}))))

    case $STATUS in
        PASS) echo "  ${DISPLAY_NAME} ${DOTS} PASS (${PASS_COUNT}/${TEST_COUNT} tests)" ;;
        FAIL) echo "  ${DISPLAY_NAME} ${DOTS} FAIL (${PASS_COUNT}/${TEST_COUNT} tests)" ;;
        SKIP) echo "  ${DISPLAY_NAME} ${DOTS} SKIP (not started)" ;;
    esac
done

echo ""
echo "  Progress: ${TOTAL_PASS}/${TOTAL_EXERCISES} exercises complete"

if [ "$TOTAL_FAIL" -gt 0 ]; then
    echo "  Status: In progress — keep going!"
elif [ "$TOTAL_PASS" -eq "$TOTAL_EXERCISES" ] && [ "$TOTAL_EXERCISES" -gt 0 ]; then
    echo "  Status: ALL COMPLETE! Move to the next module."
else
    echo "  Status: Not started — check out the exercise branch and read the README."
fi
echo ""
