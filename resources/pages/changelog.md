# Changelog

## September 5, 2011
* Added fading visual indicator of new session log entries.
* Fix duplicate log entries showing in session log.
* Fix regression which showed disabled stop button on non-new
  sessions.
* Dynamically update public address and start time on dash page.
* Imaging buttons are now disabled until instance is live.
* Improved error message when trying to add non-existent github user.

## September 2, 2011
* Updated ruby / rails quickstart. RVM root install switched out in
  favor of RVM user install.

## August 31, 2011
* Run instance-boot hook (`/etc/pair.io/hooks/instance-boot') if
  found.
* Allow org members to create repo images of org repos.

## August 30, 2011
* Fixed broken link on user config page.
* Added send email to owner when instance is up.
* Added writing of session info (like public ip) to
  `/etc/pair.io/current-session`.

## August 29, 2011
* New imaging system. Images are now second-class citizens in the
  world of pair.io, and are heavily tied to repos.
* Fixed launch erroring on private organization repos.

## August 11, 2011
* Fixed instance start erroring when user's default shell is zsh, but
  no zsh present on instance.

## August 3, 2011
* Added [instance-user hook](http://docs.pair.io/instance-config.html#instance-hooks).

## July 27, 2011
* Made github api calls more robust.
* Increased post instance request timeout.

## July 25, 2011
* Made adding owner to sudoers file more robust.

## July 21, 2011
* Added mechanism to send devs session info when they don't have a
  public email address on their github profile.
* Added section on dashboard page to show sessions you've been invited to.
* Fixed error preventing dev session page from rendering correctly.

## July 20, 2011
* Fixed bug preventing user's shell preference from being set.
* Automatically generate .gitconfig when not present.
* Fixed bug where user accounts weren't being remove correctly on imaging.
* Fixed "permission denied" error when using `tmux-shared`

## July 18, 2011
* Added `ssh gh-login@ip` text to session page.

## July 16, 2011
* Updated docs.

## July 15, 2011
* Added one-click imaging of running instances.
* Added repo config option `pairio-image`.

## July 13, 2011
* Switched from json to yaml for repo config file format.

## July 10, 2011
* Added [docs site](http://docs.pair.io).

## July 5, 2011
* Updated email smarts, better messaging around authorizing new accounts.

## July 2, 2011
* Switched out looking for users' .pair.io directories for user shell hooks.

## June 25, 2011
* Switched base ami's to Ubuntu 11.04.
* Switched base ami's from Emacs 23 to Emacs 24.

## June 24, 2011

* Added Ruby 1.9 quickstart.
* Force https.

## June 23, 2011

* Added Clojure quickstart.
* Added Rails 3 quickstart.
* Added Node.js quickstart.
