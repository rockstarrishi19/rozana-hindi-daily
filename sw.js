const CACHE='rozana-v20';
const ASSETS=['./','./index.html','./style.css?v=undo2','./app.js?v=undo2','./manifest.json','./icons/rozana-192.png','./icons/rozana-512.png','./icons/rozana-maskable-512.png'];
self.addEventListener('install',event=>event.waitUntil(caches.open(CACHE).then(cache=>cache.addAll(ASSETS)).then(()=>self.skipWaiting())));
self.addEventListener('activate',event=>event.waitUntil(caches.keys().then(keys=>Promise.all(keys.filter(key=>key.startsWith('rozana-')&&key!==CACHE).map(key=>caches.delete(key)))).then(()=>self.clients.claim())));
self.addEventListener('fetch',event=>{if(event.request.method==='GET'&&new URL(event.request.url).origin===location.origin)event.respondWith(caches.open(CACHE).then(async cache=>{try{const response=await fetch(event.request);if(response.ok)cache.put(event.request,response.clone());return response}catch{const cached=await cache.match(event.request);return cached||cache.match('./index.html')}}))});
