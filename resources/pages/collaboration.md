# Collaboration

**You are able to add collaborators that are not on the alpha list to your instances.**
They'll still be given an account on your instance,
and will be able to set their hooks, re-import keys, etc on the [user
config page](https://pair.io/config).

Pretty much the only thing non-alpha-users can't do is spin up instances.

Collaborators will need a **github account and at least one keypair**.
An email address on thier public profile is required to send them
invites at this point. No keys? No email? We recommend de-friending
them, RL style.


### Adding Users

Type their github username into the textbox under **Devs**.  You'll
see one of the following user status messages:

* **pending** - Pair will add their account to your dev instance soon
* **adding** - Adding account now.  
* **live** - Good to go.
* **need pub keys** - The user needs to authorize pair.io to read
their pub keys from github.

Additionally, you'll see the log spit out something like this:

    Adding user zoidb...
    Adding zoidb's pks to authorized_keys...
    Running user hook.
    Done adding user.

They'll be able to log in at this point. `ssh gh-login@ip`.


