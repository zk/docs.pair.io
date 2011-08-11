# Pair.io in 5 minutes: Clojure

In the next 5 minutes you'll:

* Spin up a dev instance.
* Run some tests.
* Learn how to have pair.io automatically install your dotfiles.
* Learn how to prep your instance for collaboration.

We'll use
[cheshire](https://github.com/dakrone/cheshire) as an
example. 

::spin-up-instance
## Spin up a dev instance.

1. Log in at [https://pair.io](https://pair.io).
2. Click  **New Session**.
3. Enter `dakrone/cheshire` into the text box and hit enter.
4. **Launch!**
5. Wait. Quickstarts take 2-3 minutes launch. You'll see the IP of your instance on the right, above **Logs**.
6. [Make sure **ssh-agent** is running](http://www.dribin.org/dave/blog/archives/2007/11/28/ssh_agent_leopard/) and shell into the instance:
`ssh <your github login>@<instance ip>`.

You're in!

::run-tests
## Run some tests.
* `cd ./cheshire && lein test` 

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
4. Tab back to your session page and click **Make Repo Image**.
5. Click **Make Image** then **Confirm**. Once imaging is done you'll
   see the pair.io image id pop up at the bottom of the page.
6. Fork the [pair.io imaging example repo](https://github.com/zkim/pairio-imaging-example)
   and edit `$REPO/.pair.io/config.yaml`.
7. Paste in the pair.io image id.
8. Save / commit & push.
9. Start a new pair.io session with your fork of the imaging example repo.
10. Once the instance is bootstrapping, visit
    `http://<instance ip>`. **Woot!**.
