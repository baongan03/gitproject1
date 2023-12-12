package Models;


import Utils.DateUtils;

import java.time.LocalDateTime;

public class Show implements IParseModel{
    private long idShow;
    private String showName;
    private String singer;
    private LocalDateTime timeStart;
    private LocalDateTime timeEnd;
    private ELocation location;
    private long showPrice;

    public Show() {
    }

    public Show(long idShow, String showName, String singer, LocalDateTime timeStart, LocalDateTime timeEnd, ELocation location, long showPrice) {
        this.idShow = idShow;
        this.showName = showName;
        this.singer = singer;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.location = location;
        this.showPrice = showPrice;
    }




    public long getIdShow() {
        return idShow;
    }

    public void setIdShow(long idShow) {
        this.idShow = idShow;
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalDateTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalDateTime timeEnd) {
        this.timeEnd = timeEnd;
    }

    public ELocation getLocation() {
        return location;
    }

    public void setLocation(ELocation location) {
        this.location = location;
    }

    public long getShowPrice() {
        return showPrice;
    }

    public void setShowPrice(long showPrice) {
        this.showPrice = showPrice;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s,%s,%s", idShow, showName, singer, DateUtils.formatDateTime(timeStart), DateUtils.formatDateTime(timeEnd), location, showPrice);
    }

    @Override
    public Show parse(String line) {
        String[] str = line.split(",");
        Show s = new Show(Long.parseLong(str[0]), str[1], str[2],DateUtils.parseDateTime(str[3]),
                DateUtils.parseDateTime(str[4]), ELocation.valueOf(str[5]), Long.parseLong(str[6]));
        return s;
    }
}
