package cn.saintshaga.forneus.property;

import lombok.Data;

@Data
public class JavaSetting {
    /**
     * whether the Optimizer cache enabled.
     */
    private boolean optimizerCacheEnabled = true;

    /**
     * whether to warmup the view component, set false for development phase
     */
    private boolean warmupViewComponent = false;

    /**
     * Acceptable regular expression to filter controller path
     */
    private String warmupSkeletonAcceptable = ".*";

    /**
     * Unacceptable regular expression to filter controller path
     */
    private String warmupSkeletonDeny = "com.worksap.company.framework.web..*";

    /**
     * Whether use ux log function
     */
    private boolean isUxlogMode = false;

    /**
     * the dot server url.
     */
    private String uxlogDotUrl = "https://hue-uxlog.worksap.com/company-ux-log-server";

    /**
     * Whether cache skeleton and fvmskeleton or not.
     */
    private boolean skeletonCacheEnabled = true;

    /**
     * java forneus resource root.
     */
    private String forneusResourceRoot = "";

    /**
     * Whether to load FvmSkeleton from file system, turn to "true" in production's environment
     */
    private boolean loadFvmSkeletonFromFile = false;

    /**
     * Whether using preheat in production's environment, turn to "true" in production's environment
     */
    private boolean preheatProduction = false;

    private String resourceRoot = "";

    private boolean resourceCleanOnStart = false;
    /**
     * Whether activating feedback tool
     */
    private boolean feedbackActivated = false;

    private boolean isRuntimeCompilationDetectionEnabled = false;

    /**
     * Whether to seal the Forneus View Id generation during runtime. Default value is false because it should be
     * triggered for developers to find their problem during developments.
     * <p>
     * For Production environment, please change it to false. Because this is only for developers.
     */
    private boolean forneusViewIdRuntimeGenerationSealed = false;

    /**
     * Storage for Fornues resources, such as files for Skeleton, FvmSkeleton, JS, CSS, Mapping files and other related.
     * Provided 2 kinds of storage, "development" and "integration". please choose one kind of them. <br>
     * In development environment (used by developers), choose "development. <br>
     * In evaluation and commercial environment, choose "integration".
     */
    private String resourceStorageType = "development";

    /**
     * whether to check js compiler type in warmup monitor page.
     */
    private boolean monitorJsCompilerTypeActivated = true;

    /**
     * Identify it is development mode or not.
     */
    private boolean isDevelopMode = true;

    /**
     * Use get Xml from S3 or not. Please turn on in Production environment
     */
    private boolean useMergedXmlS3AtRuntime = false;

    private boolean useComplessedFile = false;

    /**
     * Whether to add tenant account to folder structure to save resources
     */
    private boolean saveWithTenantAccount = false;

    /**
     * What type of dataBindable will be used.
     */
    private String dataBindableType = "fvm";

    private boolean reuseSkeletonFile = false;

    private boolean allowForceRefresh = false;

    private boolean acceptFirefox = false;

    private boolean acceptSafari = false;

    private boolean enableZoomChoice = true;

    /**
     * Compress partial update with gzip
     */
    private boolean partialUpdateCompressionEnabled = true;

    private int partialUpdateCompressionMinSize = 256; // kb

    private String partialUpdateCompressionEncoding = "gzip";

    /**
     * To activate HUE Talk Msa.
     */
    private boolean hueTalkMsaMode = false;

    /**
     * To enable global navi develop mode.
     */
    private boolean globalnaviMsaDevMode = false;


    private boolean disableCtrlF5ForXml = false;

}
