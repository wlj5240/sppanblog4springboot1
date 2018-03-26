package net.sppan.blog.common.param;

public class SettingsParam {
    private String siteName;
    private String siteIcp;
    private String siteBanner;
    private String siteNotice;
    private String siteDescription;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteIcp() {
        return siteIcp;
    }

    public void setSiteIcp(String siteIcp) {
        this.siteIcp = siteIcp;
    }

    public String getSiteBanner() {
        return siteBanner;
    }

    public void setSiteBanner(String siteBanner) {
        this.siteBanner = siteBanner;
    }

    public String getSiteNotice() {
        return siteNotice;
    }

    public void setSiteNotice(String siteNotice) {
        this.siteNotice = siteNotice;
    }

    public String getSiteDescription() {
        return siteDescription;
    }

    public void setSiteDescription(String siteDescription) {
        this.siteDescription = siteDescription;
    }

    @Override
    public String toString() {
        return "SettingsParam{" +
                "siteName='" + siteName + '\'' +
                ", siteIcp='" + siteIcp + '\'' +
                ", siteBanner='" + siteBanner + '\'' +
                ", siteNotice='" + siteNotice + '\'' +
                ", siteDescription='" + siteDescription + '\'' +
                '}';
    }
}
