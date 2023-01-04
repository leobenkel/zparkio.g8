![ZparkIO_Animated](https://raw.githubusercontent.com/leobenkel/zparkio.g8/main/assets/ZparkIO_animated.gif)

[![Build Status](https://travis-ci.com/leobenkel/zparkio.g8.svg?branch=main)](https://travis-ci.com/leobenkel/zparkio.g8)

# A [Giter8][g8] template for [ZparkIO][zparkio]

Gitter8 template for [ZparkIO][zparkio]

To create a ZparkIO project simply run:

```
sbt new leobenkel/zparkio.g8
```

And follow the instructions.

Look at which ZparkIO version are available on [Maven](https://search.maven.org/search?q=g:com.leobenkel%20a:zparkio*) and check out the corresponding supported [Spark versions](https://github.com/leobenkel/ZparkIO/blob/main/sparkVersions).

Version has to be above `0.12.3` to work properly.

[g8]: http://www.foundweekends.org/giter8/
[zparkio]: http://github.com/leobenkel/ZparkIO


## Development

To test giter8 locally:

```
sbt new file:///path/to/zparkio.g8
```