package nofuemagia.tatuate;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;

/**
 * Created by jlionti on 30/06/2016. No Fue Magia
 */
public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
