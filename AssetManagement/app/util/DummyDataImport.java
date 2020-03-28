
package util;

import LyLib.Interfaces.IConst;
import com.avaje.ebean.Ebean;
import models.Admin;
import models.AdminJournal;
import models.AssetCard;
import models.AssetHandle;
import models.AssetType;
import models.AssetUse;
import models.Auth;
import models.Barcode;
import models.Config;
import models.Depart;
import models.Dict;
import models.FixRecord;
import models.FixRequest;
import models.Location;
import models.Material;
import models.Member;
import models.Purchase;
import models.User;
import models.UseRequest;
import play.libs.Yaml;

import java.util.List;
import java.util.Map;

public class DummyDataImport implements IConst {
    public static void insert() {
        play.Logger.info("start load dummy data");

        if (Ebean.find(Admin.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("admin");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Admin %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Admin done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(AssetCard.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("assetCard");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default AssetCard %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default AssetCard done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(AssetHandle.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("assetHandle");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default AssetHandle %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default AssetHandle done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(AssetType.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("assetType");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default AssetType %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default AssetType done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(AssetUse.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("assetUse");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default AssetUse %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default AssetUse done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Auth.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("auth");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Auth %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Auth done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Barcode.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("barcode");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Barcode %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Barcode done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Config.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("config");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Config %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Config done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Depart.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("depart");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Depart %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Depart done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Dict.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("dict");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Dict %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Dict done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(FixRecord.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("fixRecord");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default FixRecord %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default FixRecord done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(FixRequest.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("fixRequest");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default FixRequest %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default FixRequest done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Location.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("location");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Location %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Location done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Material.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("material");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Material %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Material done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Member.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("member");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Member %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Member done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(Purchase.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("purchase");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default Purchase %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default Purchase done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(User.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("user");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default User %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default User done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

        if (Ebean.find(UseRequest.class).findRowCount() == 0) {
            try {
                Map<String, List<Object>> initData = (Map<String, List<Object>>) Yaml.load("initial-data.yml");
                List<Object> defaultObjs = initData.get("useRequest");
                if (defaultObjs != null) {
                    if (defaultObjs.size() > 0) {
                        Ebean.save(defaultObjs);
                        play.Logger.info(String.format("load dummy default UseRequest %s", defaultObjs.size()));
                    }
                }
                play.Logger.info("load dummy default UseRequest done");
            } catch (Exception ex) {
                play.Logger.error(CONFIG_FILE_ISSUE + ": " + ex.getMessage());
            }
        }

    }
}
