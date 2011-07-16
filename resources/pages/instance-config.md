# Instance Config

## Quickstarts

Quickstarts are designed to get you up and working quickly on your
repository.  They are images built with sensible default for their
respective language stacks, and can be used to quickly launch a repo
dosen't have a `.pair.io/config.yaml`.

You can force a particular quickstart by adding the following to your
`config.yaml`:

    # Force quickstart.
    quickstart: ruby19 # | clojure | rails3 | nodejs

See the [quickstarts page](/quickstarts.html) for more information.

## config.yaml

Pair.io looks for a config.yaml file in the `.pair.io` directory of
your repository.  This config file allows you to specify what to
launch and provision.

There are four core configuration options that are required by pair.io
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
* `pairio-image: 4e1fdeebe4b03f4db7d4c829-zkim-zkim/cljs` - Use
  pair.io image.


## Pair.io Images

You have the option to create an image from a running dev instance at
any time.  This lets you achieve short launch times while still
retaining full control over the configuration of your instance.

You'll find the imaging controls by clicking **Make Repo Image** on
your session page.  Please note that **imaging will terminate your instance**.


## Example Config File

<pre><code class="small">
# $REPO/.pair.io/config.yaml

# Quickstarts
quickstart: empty # | ruby19 | rails3 | nodejs | clojure

# Pair.io gives you fine-grained control over what we provision for
# you. Here's the full set of config options:

image-id: ami-1aad5273 # must be in the us-east-1 region
size: large # micro | small | large
inbound-ports: 
  - 22  # Make sure you've got 22 so pair can connect
        # to your instance.
  - 80
  - 81
  - "tcp:1.2.3.4/32:443"
  - "udp:0.0.0.0/0:1234"
provision: sh # none | sh | chef

# when chef (http://wiki.opscode.com/display/chef/Chef+Solo)
chef:
  config: solo.rb # chef-solo -c flag
  json-attributes: node.json # -j flag
# recipe-url: http://example.com/chef-solo.tar.gz # -r flag


# Pair.io images, quickstart-like launch times with your own config.
pairio-image: 4e1fdeebe4b03f4db7d4c829-zkim-zkim/cljs
</code></pre>



