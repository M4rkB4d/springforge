# Exercise: Write Migration V3

YOUR TASK: Create a file called `V3__add_tags_table.sql` in `src/main/resources/db/migration/`

The migration should:
1. Create a `tags` table with columns:
   - id BIGSERIAL PRIMARY KEY
   - name VARCHAR(50) NOT NULL UNIQUE
2. Create an `article_tags` join table with columns:
   - article_id BIGINT NOT NULL REFERENCES articles(id)
   - tag_id BIGINT NOT NULL REFERENCES tags(id)
   - PRIMARY KEY (article_id, tag_id)

Naming convention: V{number}__{description}.sql (two underscores!)
