package io.realm;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.MarkerManager;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * An implementation of the {@link ClusterManager} that handles processing a {@link RealmResults}
 * and converting the RealmObjects into {@link RealmClusterItem}s for background thread use.
 */
public class RealmClusterManager<M extends RealmObject & ClusterItem>
        extends ClusterManager<RealmClusterItem<M>> {

    private Map<M, RealmClusterItem<M>> itemMap = new HashMap<>();

    public RealmClusterManager(Context context, GoogleMap map) {
        super(context, map);
    }

    public RealmClusterManager(Context context, GoogleMap map, MarkerManager markerManager) {
        super(context, map, markerManager);
    }

    public RealmClusterItem<M> getClusterItem(M item) {
        return itemMap.get(item);
    }

    public void updateRealmResults(RealmResults<M> realmResults) {
        clearItems();
        itemMap.clear();
        if (realmResults == null || !realmResults.isValid() || !realmResults.isLoaded()) return;

        List<RealmClusterItem<M>> items = new ArrayList<>(realmResults.size());
        for (M item : realmResults) {
            if (item.isValid() && item.getPosition() != null) {
                RealmClusterItem<M> clusterItem = new RealmClusterItem<>(item);
                items.add(clusterItem);
                itemMap.put(item, clusterItem);
            }
        }
        super.addItems(items);
        cluster();
    }
}
