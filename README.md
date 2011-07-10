# docs.pair.io

## Usage

From shell:

     lein run -m regen

From slime:

    (use 'pairio-docs.core)
    (def stop (auto-regen site 500)) ; Regenerate site every 500 ms

    ;; Open up ./html/*.html to see your changes.

    (stop)

Open ./html/index.html

Thanks for contributing.

## TODO

* `auto-regen` needs to track file modification times and regen only
  if files have chaged.  If this annoys you to the point of wanting to
  fix it (it hasn't for me yet), please do.

## License

Copyright (C) 2011 Zachary Kim

Distributed under the EPL v1.0
