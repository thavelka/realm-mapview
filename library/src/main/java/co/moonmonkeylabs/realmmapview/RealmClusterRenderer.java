package co.moonmonkeylabs.realmmapview;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import io.realm.RealmClusterManager;
import io.realm.RealmClusterItem;
import io.realm.RealmObject;

/**
 * A custom cluster/marker renderer that handles reading the title from a
 * {@link io.realm.internal.Row}. This class could be extended or further implemented to handle
 * more customization of the displayed markers.
 */
public class RealmClusterRenderer<M extends RealmObject & ClusterItem>
        extends DefaultClusterRenderer<RealmClusterItem<M>> {

    private RealmClusterManager<M> clusterManager;

    public RealmClusterRenderer(
            Context context,
            GoogleMap map,
            RealmClusterManager<M> clusterManager) {
        super(context, map, clusterManager);
        this.clusterManager = clusterManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void onBeforeClusterItemRendered(
            RealmClusterItem item,
            MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);
        markerOptions.title(item.getTitle());
    }
}
