# Rozana

A responsive Hindi daily inspiration feed and installable Android PWA. It rotates the first featured card by local date, with one-card-at-a-time scrolling, category filters, likes and saved items in local storage, native share/copy, and JPEG downloads.

## Run locally

Open `index.html` for the website. For service-worker/PWA behavior, serve this folder over HTTP, for example:

```sh
python3 -m http.server 8000
```

Then visit `http://localhost:8000`.

## Publish and update

The site is published on GitHub Pages from the `gh-pages` branch. Its public URL is https://rockstarrishi19.github.io/rozana-hindi-daily/.

For a daily update, edit the `entries` list in `app.js`, then run `./publish.sh`. It saves the edit on `main` and updates the published `gh-pages` branch. The service worker checks the network first and uses its saved copy when offline, so published edits can reach returning visitors without changing a cache number each day. News cards should be refreshed with dated, linked sources before each daily publication.

## Install on Android

Open the website in Chrome on Android, tap **Android ऐप इंस्टॉल करें**, then confirm **Install**. If Chrome has not offered the install prompt yet, use its menu (⋮) and choose **Install app** or **Add to Home screen**. The installed app opens RozAna in a standalone window and keeps the website's saved feed available offline after it has loaded once.

## Add content

Edit the `entries` array at the top of `app.js`. Each item uses `category`, `icon`, `label`, `text`, `sub`, `by`, and `type`; trending or factual items can also include `sourceUrl` and `sourceLabel` to show a source link. The 12 categories are Motivation, Shayari, Facts, Psychology, Daily Knowledge, Lessons from Books, Relationships, Words of Calm, Habits & Discipline, Riddle, Word of the Day, and Life Lessons (shown with Hindi and English labels in the app). Cards download as JPEG images.

News and search-trend cards in this starter feed were checked against the linked primary sources on 20 October 2026. The site is a static feed: those headlines do not refresh automatically. Update dated entries in `app.js` when adding new daily trends. When the supplied Hindi book PDFs are available, their text can be curated into entries with book and author attribution.
