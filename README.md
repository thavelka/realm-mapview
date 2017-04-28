## A MapView that is powered by Realm

A simple, yet powerful wrapper around the `MapView` that renders a RealmResults with support for clustering and real-time updates.

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
Setup is a breeze:
1. Implement ClusterItem in your model class
```
public class Business extends RealmObject implements ClusterItem {

    public int id;
    public String name;
    public String description;
    public Float latitude;
    public Float longitude;
    
    // Provide marker location, title, and description
    @Override
    public LatLng getPosition() { // marker location
        if (latitude != null && longitude != null) {
            return new LatLng(latitude, longitude);
        } else {
            return null;
        }
    }

    @Override
    public String getTitle() { // marker title
        return name;
    }

    @Override
    public String getSnippet() {
        return description;
    }
}
```

2. Subclass RealmClusterMapFragment and provide the data in getRealmResults()
```
public class BusinessRealmClusterMapFragment extends RealmClusterMapFragment<Business> {

    Realm realm;
    RealmResults<Business> realmResults;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        realm = Realm.getDefaultInstance();
        realmResults =  realm.where(Business.class)
                .isNotNull("latitude")
                .isNotNull("longitude").findAll();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        realm.close();
    }

    @Override
    protected RealmResults<Business> getRealmResults() {
        return realmResults;
    }
}
```

3. Add the fragment to the UI in your Activity or Fragment
```
BusinessRealmClusterMapFragment fragment = new BusinessRealmClusterMapFragment();
getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
```

That's it! The fragment will handle showing the markers on the map, clustering, and updating the map when the content of the RealmResults changes.

## Customization:
You can customize the appearance, features, and behavior of the map by overriding methods in your RealmClusterMapFragment. To see what's possible, read through the [`RealmClusterMapFragment` source](./library/src/main/java/co/moonmonkeylabs/realmmapview/RealmClusterMapFragment.java) and [sample project code](./example/app/src/main/java/co/moonmonkeylabs/realmmap/example).
Here are just a few possibilities: 
* Render as a satellite map
* Show user's location
* Enable/disable controls or movement gestures
* Set default location and zoom level
* Set bounds for the map
* Perform an action when a map item is clicked
* Disable/customize clustering behavior
* Modify cluster appearance

## Other:

Your projects's `AndroidManifest` has to include the following valid Map V2 key. You can obtain a key from [Google Developer Console](https://developers.google.com/maps/documentation/android-api/)

```
<meta-data
    android:name="com.google.android.maps.v2.API_KEY"
    android:value="YOUR_KEY"/>
```

## Feedback/More Features:
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
