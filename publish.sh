#!/usr/bin/env bash
set -euo pipefail

git add README.md app.js index.html manifest.json style.css sw.js publish.sh
if ! git diff --cached --quiet; then
  git commit -m "Update RozAna daily content"
fi
git push origin main
git fetch origin gh-pages

publish_dir="$(mktemp -d /tmp/rozana-pages.XXXXXX)"
cleanup() {
  git worktree remove --force "$publish_dir" >/dev/null 2>&1 || true
  rm -rf "$publish_dir"
}
trap cleanup EXIT

git worktree add --detach "$publish_dir" origin/gh-pages
cp index.html app.js style.css sw.js manifest.json "$publish_dir/"
touch "$publish_dir/.nojekyll"
git -C "$publish_dir" add -A
if ! git -C "$publish_dir" diff --cached --quiet; then
  git -C "$publish_dir" commit -m "Publish RozAna update"
  git -C "$publish_dir" push origin HEAD:gh-pages
fi
