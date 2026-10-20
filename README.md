# Rozana

A responsive Hindi daily inspiration feed. It rotates the first featured card by local date, with one-card-at-a-time scrolling, category filters, likes and saved items in local storage, native share/copy, JPEG downloads, and installable PWA support.

## Run locally

Open `index.html` for the website. For service-worker/PWA behavior, serve this folder over HTTP, for example:

```sh
python3 -m http.server 8000
```

Then visit `http://localhost:8000`.

## Publish and update

The site is prepared for GitHub Pages. After signing in to GitHub on the publishing machine, create a GitHub repository for this folder, push the `main` branch, and set the repository's Pages source to **GitHub Actions**. The workflow in `.github/workflows/pages.yml` publishes the site on every push to `main` and on manual runs. The public URL will appear in the repository's **Settings → Pages** after the first successful run.

For a daily update, edit the `entries` list in `app.js`, then commit and push to `main`; GitHub Actions will redeploy it. The service worker now checks the network first and uses its saved copy when offline, so published edits can reach returning visitors without changing a cache number each day. News cards should be refreshed with dated, linked sources before each daily publication.

## Add content

Edit the `entries` array at the top of `app.js`. Each item uses `category`, `icon`, `label`, `text`, `sub`, `by`, and `type`; trending or factual items can also include `sourceUrl` and `sourceLabel` to show a source link. The 12 categories are Motivation, Shayari, Facts, Psychology, Daily Knowledge, Lessons from Books, Relationships, Words of Calm, Habits & Discipline, Riddle, Word of the Day, and Life Lessons (shown with Hindi and English labels in the app). Cards download as JPEG images.

News and search-trend cards in this starter feed were checked against the linked primary sources on 20 October 2026. The site is a static feed: those headlines do not refresh automatically. Update dated entries in `app.js` when adding new daily trends. When the supplied Hindi book PDFs are available, their text can be curated into entries with book and author attribution.
