## A MapView that is powered by Realm

A simple, yet powerful wrapper around the `MapView` with support for clustering and built-in support for querying and rendering a Realm results.

## How To Include It:

In project build.gradle:
```
	repositories {
        // ...
        maven { url "https://jitpack.io" }
    }
```

In app module build.gradle:
```
	dependencies {
	        compile 'com.github.thavelka:realm-mapview:1.0.0'
	}
```

## Demo

![Screenshot](https://raw.githubusercontent.com/thorbenprimke/realm-mapview/master/extra/screenshot-demo-app.gif)

## How To Get Started:

## Other:

Your projects's `AndroidManifest` has to include the following valid Map V2 key. You can obtain a key from [Google Developer Console](https://developers.google.com/maps/documentation/android-api/)

```
<meta-data
    android:name="com.google.android.maps.v2.API_KEY"
    android:value="YOUR_KEY"/>
```

##Feedback/More Features:
I would love to hear your feedback. Do you find the ```RealmClusterMapFragment``` useful? What functionality are you missing? Open a ```Github``` issue and let me know. Thanks!


## License
```
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

Included dependencies are:
Realm (https://github.com/realm/realm-java)
Google Maps Android API utility library (https://github.com/googlemaps/android-maps-utils)
```
