# RozAna ko Blogger ya WordPress par lagane ka guide

## ZIP me kya hai

- `index.html` — website ka page structure
- `style.css` — design, responsive layout aur animations
- `app.js` — 12 categories ka content, shayari/facts/quotes, filters, like/save/share aur JPEG download
- `manifest.json`, `sw.js` — PWA install aur offline cache settings
- `icons/` — website/PWA ke icons
- `EMBED-CODE.html` — Blogspot/WordPress page me paste karne ke liye ready iframe

Saara quote aur category content `app.js` me hai. Roz ka content update isi file ki `entries` list me karein. ZIP ko sambhal kar rakhein; yeh website source ka backup hai.

**Saved items ka note:** Like aur save ki hui entries visitor ke apne browser me rehti hain. Woh ZIP, website server ya doosre phone me automatically sync nahi hoti. Content `app.js` me rehta hai; har visitor ki personal pasand alag browser storage me hoti hai.

## Blogspot / Blogger

### Full page ke roop me embed

1. Blogger dashboard me apna blog kholkar **Pages → New Page** chunein.
2. Page ka naam `RozAna` rakhein.
3. Editor me HTML view chunein, `EMBED-CODE.html` ka code paste karein aur page publish karein.
4. Page ko navigation me dikhane ke liye **Layout → Pages** gadget me is page ko enable karein.

Google ke help page ke mutabik Pages banakar unhe Pages gadget ke zariye blog navigation me dikhaya ja sakta hai. Agar page editor iframe code hata de, apni theme me **Layout → Add a Gadget → HTML/JavaScript** try karein ya nav me RozAna ki website ka link add karein.

### Seedha link

Navigation me `RozAna` naam ka link jodein aur URL rakhein:

`https://rockstarrishi19.github.io/rozana-hindi-daily/`

Seedha link sabse saral hai; iframe embed karne par Blogger ka apna header/footer bhi page par nazar aa sakta hai.

## WordPress

### WordPress.com

Page edit karein, **Custom HTML** block jodein aur `EMBED-CODE.html` paste karein. WordPress.com par iframe jaise custom code ke liye paid plan ke saath hosting features active hona zaroori ho sakta hai. Agar code save karne par hat jaye, page me RozAna ka normal link/button add karein.

### Self-hosted WordPress (WordPress.org)

Page me **Custom HTML** block use karke iframe paste karein. Agar WordPress code ko sanitize karke iframe hata de, apne admin account/capability ke saath check karein ya seedha RozAna link/button lagayein. WordPress roles ke hisab se `<iframe>` aur `<script>` jaise tags filter kar sakta hai.

## Website ko apne host par chalana

1. ZIP extract karein; `index.html`, `app.js`, `style.css`, `manifest.json`, `sw.js`, aur `icons/` ek hi folder structure me rakhein.
2. Is folder ko static hosting par upload karein. Website ko `file://` se kholne par service worker/PWA install nahi chalta; local preview ke liye folder me terminal kholkar `python3 -m http.server 8000` chalayein aur `http://localhost:8000` kholein.
3. Agar domain badlein, `manifest.json` ka `start_url` aur `scope` check karein. `index.html` me resource links relative hain, isliye folder structure waise hi rakhein.

RozAna ke current live page ka link `EMBED-CODE.html` me diya hai. Blogspot/WordPress iframe us published page ko dikhata hai; `app.js` me content update karne ke baad dobara embed code badalne ki zaroorat nahi.

## Official platform help

- Blogger Pages: https://support.google.com/blogger/answer/165955
- Blogger theme HTML/CSS: https://support.google.com/blogger/answer/176245
- WordPress.com Custom HTML aur plan limits: https://wordpress.com/support/wordpress-editor/blocks/custom-html-block/
- WordPress.org Custom HTML block aur sanitization: https://wordpress.org/documentation/article/custom-html/
