# Instance Config

Instance config is the _what_ you're spinning up: server size and type
(arch), firewall config, operating system, etc.

::quickstarts
## Quickstarts

Quickstarts are designed to get you up and working quickly on your
repository.  They are images built with sensible default for their
respective language stacks, and can be used to quickly launch a repo
that dosen't have a `$REPO/.pair.io/config.yaml`.

You can force a particular quickstart by adding the following to your
`config.yaml`:

    # Force ruby quickstart
    quickstart: ruby19 # | clojure | rails3 | nodejs

::config-yaml
## Config.yaml

Pair.io looks for a config.yaml file in the `.pair.io` directory of
your repository.  This config file allows you to specify what to
launch and provision.

There are four core key-value pairs that are required by pair.io
to configure your instance:

* `image-id` - The AMI id.
* `size` - Size of the server. Valid values at this time are `micro`,
  `small`, and `large`, and correspond to AWS sizes t1.micro,
  m1.small, and m1.large.
* `inbound-ports` - Firewall configuration. A list of integers, or
  strings denoting which inbound ports to make accessable.  See below
  for string format.
* `provision` - What to do to configure your instance.  Valid values
  at this time are `none`, `sh`, <del><code>chef</code></del>,
  <del><code>pallet</code></del>, and <del><code>puppet</code></del>.

<p class="aside">
   3rd party provisioners are temporarily disabled. Working on a fix.
</p>


All other configuration options (such as `quickstart: ruby19` above)
are sugar for specifying the above.

* `quickstart: ruby19` - Use quickstart.

::pairio-images
## Pair.io Images

You have the option to create an image from a running dev instance at
any time.  This lets you achieve short launch times while still
retaining full control over the configuration of your instance.

See [imaging your instance](/imaging.html) for more information.


::instance-hooks
## Instance Hooks


If found, pair.io will run the script found at `/etc/pair.io/hooks/instance-user` 
as root with `$1` set to the user's login.  You can use this script to set up user
specific state, such as initializing rvm builds or seed the user's 
`.m2/repository`.

The following example seeds a user's m2 repo:

    #!/bin/sh

    LOGIN=$1

    cp -R /etc/pair.io/.m2 /home/$LOGIN/
    chown -R $LOGIN:$LOGIN /home/$LOGIN/.m2

Unsuccessful execution of this script (exit code != 0) will result in printing the
output of the failed run to the log on your session page, and can be used for 
debugging (or, you could just run it yourself prior to imaging).

::example-config-file
## Example Config File

Precedence: `quickstart` -> `pairio-image` -> rest.

<pre><code class="small">
# $REPO/.pair.io/config.yaml

# Quickstarts
# Quickstarts override all required keys (image-id, size,
# inbound-ports, and provision)
quickstart: empty # | ruby19 | rails3 | nodejs | clojure

# Pair.io gives you fine-grained control over what we provision for
# you. Here are the full set of config options:

image-id: ami-1aad5273 # must be in the us-east-1 region
size: large # micro | small | large
inbound-ports: 
  - 22  # Make sure you've got 22 so pair can connect
        # to your instance.
  - 80
  - 81
  - "tcp:1.2.3.4/32:443"
  - "udp:0.0.0.0/0:1234"
provision: none

</code></pre>



