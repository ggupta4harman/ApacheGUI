package net.apachegui.history;

import net.apachegui.db.LogDataDao;
import net.apachegui.db.SettingsDao;
import net.apachegui.global.Constants;

import org.apache.log4j.Logger;

public class HistoryMaintenance {
    private static Logger log = Logger.getLogger(HistoryMaintenance.class);

    /**
     * Thread used to shrink log data and rebuild insert date index for efficiency.
     */
    public void clean() throws Exception {

        if (SettingsDao.getInstance().getSetting(Constants.INIT) != null) {
            log.trace("Shrinking LogData");

            LogDataDao.getInstance().shrinkLogData(Integer.parseInt(SettingsDao.getInstance().getSetting(Constants.HISTORY_RETENTION)));
            // wake up once an interval
        } else {
            log.trace("ApacheGUI has not been initialized, going back to sleep");
        }

    }

}
