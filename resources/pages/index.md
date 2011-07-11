# General

Pair.io gives you a one-button,
collaboration-friendly dev box for your [github](https://github.com)
repo. 

## What's this about?

Repeatable, disposable dev instances.

  * New dev? Get up and running in minutes.
  * Mis-type `rm -rf ./` to your heart's content.
  * No leaking project-specific state.

Real-time collaboration. Here's what they'll need:

  * One (*1*) github account.
  * One (*1*) key pair.
  * One (*1*) terminal.
  * One (*1*) half-decent pipe.

Plus, you get a nice UX around the whole thing. Grab your dotfiles, it's going to be a good time.

## Getting Started

There's an easy way, and an easy-but-slightly-more-time-consuming way to get
started.  We'll cover the easy way here (quickstarts). See the
[instance config page](/instance-config.html) for the other one.

You'll need to have a Clojure, Rails 3, Ruby 1.9, or NodeJS 0.4.8
project in mind. We'll use
[cheshire](https://github.com/dakrone/cheshire) as an example.
  
1. Log in at [https://pair.io](https://pair.io).
2. Click  **New Session**.
3. Enter `dakrone/cheshire` into the text box and hit enter.
4. **Launch!**
5. Wait. Quickstarts take 2-3 minutes to get to live. You'll see the IP of your instance on the right, above **Logs**.
6. Make sure **ssh-agent** is running and shell into the instance: `ssh gh-login@ip`.
7. `cd ./cheshire && lein test`

You should see:
    
    ...
    Testing cheshire.test.core
    Testing cheshire.test.custom
    Ran 29 tests containing 36 assertions.
    0 failures, 0 errors.

Woot!

* There's a `tmux-shared` helper script in
`/usr/bin`.  First run sets up a shared tmux socket
session. Subsequent runs connect to that socket session.

### Ok, lets try a ruby project.

  * Launch `thoughtbot/factory_girl`
  * `cd ./factory_girl`
  * `rvmsudo bundle install`
  * `rake spec`

<p class="aside">
   Ruby friends: is root rvm too clunky? 
   &nbsp;
   &nbsp;
   Vote please -- 
   <a href="http://goo.gl/5tG1t">Too clunky.</a>
   &nbsp;
   <a href="http://goo.gl/THCXD">Nah, it's fine.</a>
</p>

You should see:

    ...
    Finished in 1.1 seconds
    202 examples, 0 failures
    ...
    Finished in 4.86 seconds
    102 examples, 0 failures
