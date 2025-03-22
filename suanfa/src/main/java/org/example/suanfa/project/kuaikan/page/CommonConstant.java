package org.example.suanfa.project.kuaikan.page;

public interface CommonConstant {

  // 客户端收到-1的值时，就不展现fetched order的信息
  long INVALID_FETCHED_ORDER = -1;
  // 表示需要读db
  long INVALID_SUBJECT_ID = -1;

  int TOPLIST_PAGE_SIZE = 100;

  String CLIENT_INFO_KEY = "clientInfo";
  String USER_AGENT_KEY = "User-Agent";

  int PAGESIZE = 20; // 分页每页行数
  String CDNURL = "http://f2.kkmh.com/";
  String CDN_HOST_STAG = "http://f12stag.kkmh.com/";

  String AUDIO_HOST = "https://gameimg.kkmh.com/";

  // 活动相关
  int NOT_VIP = -1;
  int VIP_EXPIRED = 0;
  int VIP = 1;

  // 活动领取抽卡券张数
  int ACTIVITY_CARD_PACK_COUNT = 1;
  String COLLECTION_COMPLETE_REWARD_ACTIVITY = "collectionCompleteRewardActivity";
  String MINE_PAGE_GIVE_AWAY_ACTIVITY = "minePageGiveAwayActivity";
  String VIP_ACTIVITY_NAME = "vipCardPackActivity";
  String POINT_TASK_NAME = "pointTask";

  int DENOMINATOR = 10000;

  int NEW_USER = 1; // 新用户，创建userInfo表时使用.
  int NOT_NEW_USER = 0; // 老用户，创建userInfo表时使用.

  int SUBJECT_CAROUSEL_DRAW_CARD = 1; // 专题页轮播类型抽卡
  int SUBJECT_CAROUSEL_COLLECTION = 2; // 专题页轮播类型集齐

  // 生成订单时，订单详情用到的别名
  String LOTTERY_CHANCE_ALIAS_NAME = "抽奖付费";

  // 卡片预约页面配置
  String APPOINTMENT_CONFIG_PREFIX = "appointment:h5config:";

  int ALL_SUBJECT = 0;
  int ALL_COMIC = 0;
  int ALL_CARD_PACK = 0;

  String SHINE_CARD_PRIZE_CONFIG_PREFIX = "shine:card:subject:prize:";

  // 展览馆专题分组配置
  String GALLERY_SUBJECT_GROUP_CONFIG_KEY_PATTERN = "gallery:subject:group:config:%s";
  int CARD_PACK_V2_PAGE_SIZE = 32;
  int GALLERY_PAGE_SIZE = 3;

  int COLLECTING_QUEUE_PAGE_SIZE = 12;

  // 卡片合成相关常量
  String COMPOSITION_QUICKEN_PRODUCT_NAME = "加速合成";

  // 获取有赞token接口的URL
  String ACCESS_YOUZAN_TOKEN = "https://admin.quickcan.com/api/youzan/access_token/get";
}
