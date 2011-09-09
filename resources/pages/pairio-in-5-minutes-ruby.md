# Pair.io in 5 minutes: Ruby

In the next 5 minutes you'll:

* Spin up a dev instance.
* Run some tests.
* Learn how to have pair.io automatically install your dotfiles.
* Learn how to prep your instance for collaboration.

We'll use thoughtbot's [factory_girl](https://github.com/thoughtbot/factory_girl) library as an example.

::spin-up-instance
## Spin up a dev instance.

1. Log in at [https://pair.io](https://pair.io).
2. Click  **New Session**.
3. Enter `thoughtbot/factory_girl` into the text box and hit enter.
4. **Launch!**
5. Wait. Quickstarts take 4-6 minutes to launch. You'll see the IP of your instance on the right, above **Logs**.
6. `ssh <your github login>@<instance ip>`.

You're in!

## RVM setup                                                                                                                                                                                               

The Ruby quickstart should have set you up with a
`$HOME/.bash_profile` and a `$HOME/.zshrc` if they don't already
exist.  The purpose of this automatic setup is to get RVM working
correctly.  You can verify that RVM's working correctly by running
`rvm` in your shell.

If `rvm` fails, put the following in your shell's dotfile:                                                                                                                                                 

<code class="small">
[[ -s "$HOME/.rvm/scripts/rvm" ]] && source "$HOME/.rvm/scripts/rvm"                                                                                                                                    
</code>

Exit out of the instance, and log back in. RVM should be working                                                                                                                                           correctly now.           

::run-tests
## Run some tests.
* `cd ./factory_girl && bundle install && bundle exec rake spec`

You should see:

    ...
    Finished in 1.1 seconds
    202 examples, 0 failures
    ...
    Finished in 4.86 seconds
    102 examples, 0 failures

Woot!

* There's a `tmux-shared` helper script in
`/usr/bin`.  First run sets up a shared tmux socket
session. Subsequent runs connect to that socket session.

::dotfiles
## Dotfiles

You've got a dev instance with your repo on it, but if you're going to
get any serious work done you'll need your dotfiles as well.

Pair.io's [shell command hook](https://pair.io/config) is run after
your account is added to a dev instance, and can be used to
automatically install your dotfiles. Let's simulate the shell hook on
your running instance:

1. Tab over to your instance.
2. Enter <code class="small">curl
https://raw.github.com/zkim/.pair.io/master/bootstrap | sh</code> into
the command prompt and hit enter.  
3. Your (my) dotfiles are now installed. Run a new `zsh` to see this in action.

::collaborate
## Collaborate

Your cohort will need a github account, at least one public key on the
account, and an email address on their public profile.

* Tab back to your session page.
* Type in cohort's github username into the text box under **Devs** and hit enter.
* You'll see one of two status messages: **pending** or **need pub keys**. 
  <span class="aside">
   <a href="/collaboration.html#adding-users">See &apos;Adding Users&apos;</a>.
  </span>
* Click the **send invite** button to send your cohort an invitation email
  containing instructions on how to authorize and connect to your
  dev instance.
* In your shell. Run `tmux-shared`, which will set up a
  collaborative tmux session.
* Tell your cohort to run `tmux-shared` once they've logged into the
  box.
* You and your cohort should be able to see the same shell/editor/cli.
* Hack on some code together, perhaps using something like
  [Skype](http://skype.com) or [Google Talk](http://www.google.com/talk/)
  to chat with each other.


::5-addtl-minutes
# Have another 5 minutes?

* Image your configured instance for future use.

::image-your-instance
## Image Your Instance

Imaging is a way to achieve short launch times while retaining full
control over the configuration of your instance.  


1. Tab over to your shell.
2. `sudo apt-get install -y apache2`
3. `sudo sh -c 'echo woot! > /var/www/index.html'`
4. Tab back to your session page and click **Gen Personal Image**.
5. Enter a description, and click **Make Image!** then
   **Confirm**. You'll get an email when your image is ready.
6. Start a new pair.io session with with
   `thoughtbot/factory_girl`. You'll see your new personal image is
   selected by default. Launch!
7. Once the instance is bootstrapping, visit
    `http://<instance ip>`. **Woot!**.
