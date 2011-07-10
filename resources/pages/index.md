# General

Pair.io gives you a one-button,
collaboration-friendly dev box for your [github](https://github.com)
repo. 

Polish up your dotfiles, it's going to be a good time.

## What's this about?

Repeatable, disposable dev instances.

  * New dev / project? Get up and running in minutes.
  * Mis-type `rm -rf ./` to your heart's content.
  * No leaking project-specific state.

Real-time collaboration. Here's what they'll need:

  * One (*1*) github account.
  * One (*1*) key pair.
  * One (*1*) terminal.
  * One (*1*) half-decent pipe.

Plus, you get a nice UX around the whole thing.

## Getting Started

There's an easy way, and an easy-but-slightly-more-time-consuming way to get
started.  We'll cover the easy way here (quickstarts). See the
[instance config page](/instance-config.html) for the other one.

You'll need to have a Clojure, Rails 3, Ruby 1.9, or NodeJS 0.4.8
project in mind.
  
1. Log in at [https://pair.io](https://pair.io).
2. Click  **New Session**.
3. Enter the repo identifier in `:login/:name` format and hit enter.  For example,
`zkim/docs.pair.io` (this site).
4. Check that the quickstart chosen for you makes sense.
5. **Launch!**
6. Wait. Quickstarts take 2-3 minutes to get to live. You'll see the IP of your instance on the right, above **Logs**.
7. Make sure **ssh-agent** is running and shell into the instance: `ssh login@XXX.XXX.XXX.XXX`.
8. Grab your dotfiles.
9. Write some code.

Woot!

* There's a `tmux-shared` helper script in
`/usr/bin`.  The first time it runs it will set up a shared tmux socket session. Subsequent runs connect to that socket session.


