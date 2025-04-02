package org.example.suanfa.project.kuaikan.common;



public class CardBattleResponseCodeMsg extends ResponseCodeMsg {

    public static final CardBattleResponseCodeMsg ILLEGAL_PARAM = new CardBattleResponseCodeMsg(10001, "参数非法");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_NO_FOUND_ACTIVITY_CONFIG = new CardBattleResponseCodeMsg(10002, "探索活动不存在");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_EXPLORE_ERROR = new CardBattleResponseCodeMsg(10003, "探索失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_NO_FOUND_EXPLORE_TASK = new CardBattleResponseCodeMsg(10004, "探索记录不存在");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_OBTAIN_PRIZE_ERROR = new CardBattleResponseCodeMsg(10005, "探索奖励领取失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_UPDATE_SELECT_CARD_FAIL = new CardBattleResponseCodeMsg(10006, "更新选择卡牌失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_CHECK_FAIL = new CardBattleResponseCodeMsg(10007, "战斗校验为失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_COMPUTE_PRIZE_ERROR = new CardBattleResponseCodeMsg(10008, "卡牌战斗发奖失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_UPDATE_BATTLE_STATUS_FAIL = new CardBattleResponseCodeMsg(10009, "更新战斗状态失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_TASK_COMPLETED = new CardBattleResponseCodeMsg(10010, "战斗任务已完成");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_REPORT_RESULT_REPEAT = new CardBattleResponseCodeMsg(10011, "战斗结果重复上报");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_CARD_SELECTED_FAIL = new CardBattleResponseCodeMsg(10012, "获取卡牌列表失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_TOPIC_SELECTED_FAIL = new CardBattleResponseCodeMsg(10013, "获取卡牌专题列表失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_START_BATTLE_ERROR = new CardBattleResponseCodeMsg(10014, "准备战斗信息异常");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_MONSTER_NOT_EXIST = new CardBattleResponseCodeMsg(10015, "战斗对象不存在");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_LOCK_FAIL_PROMPT = new CardBattleResponseCodeMsg(10016, "请稍后再试");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_SMART_SELECT_FAIL = new CardBattleResponseCodeMsg(10017, "快速上阵失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_ROLE_PLAYER_NOT_EXIST = new CardBattleResponseCodeMsg(10018, "战斗角色不存在");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_CONFIG_NOT_EXIST = new CardBattleResponseCodeMsg(10019, "战斗配置不存在");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_SELL_CARD_FAIL = new CardBattleResponseCodeMsg(10020, "售卡失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_ONE_CLICK_SELECT_FAIL = new CardBattleResponseCodeMsg(10021, "一键选卡失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_UP_CARD_FAIL = new CardBattleResponseCodeMsg(10022, "升级卡失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_BREAK_CARD_FAIL = new CardBattleResponseCodeMsg(10023, "突破卡失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_USER_PROP_ADD_FAIL = new CardBattleResponseCodeMsg(10024, "用户道具添加失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_USER_PROP_OBTAIN_FAIL = new CardBattleResponseCodeMsg(10025, "用户材料获取失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_USER_PROP_USE_FAIL = new CardBattleResponseCodeMsg(10026, "用户道具使用失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_USER_PROP_NOT_ENOUGH = new CardBattleResponseCodeMsg(10027, "用户材料不足");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_USER_POINT_NOT_ENOUGH = new CardBattleResponseCodeMsg(10028, "兑换失败，战斗点数不足");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_TASK_NOT_EXIST = new CardBattleResponseCodeMsg(10029, "战斗关卡不存在");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_ONE_CLICK_SELECT_MATERIAL_FAIL = new CardBattleResponseCodeMsg(10030, "当前无可一键选择的材料");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_ONE_CLICK_SELECT_COIN_FAIL = new CardBattleResponseCodeMsg(10031, "当前升级所需的银币不足");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_ONE_CLICK_SELECT_ADJECTIVE = new CardBattleResponseCodeMsg(10032, "当前升级经验已满");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_POINT_EXCHANGE_COIN_FAIL = new CardBattleResponseCodeMsg(10033, "点数兑换银币失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_TASK_WAIT_FREE_TOPIC_VALID = new CardBattleResponseCodeMsg(10034, "该等免专题已在其它关卡生效");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_TASK_TOPIC_VALID = new CardBattleResponseCodeMsg(10035, "该专题已在其它关卡生效");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_USER_POINT_CONSUME_FAIL = new CardBattleResponseCodeMsg(10036, "战斗点数扣除失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_TASK_UPDATE_FAIL = new CardBattleResponseCodeMsg(10037, "更新卡牌关卡失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_ACTIVITY_UPDATE_FAIL = new CardBattleResponseCodeMsg(10038, "更新探索活动失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_FEED_CREATE_ORDER_FAIL = new CardBattleResponseCodeMsg(10039, "创建加料订单异常");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_FEED_ORDER_QUERY_FAIL = new CardBattleResponseCodeMsg(10040, "查询加料订单异常");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_NO_FOUND_COPY_ACTIVITY_CONFIG = new CardBattleResponseCodeMsg(10041, "副本不存在");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_COPY_ACTIVITY_UPDATE_FAIL = new CardBattleResponseCodeMsg(10042, "更新副本失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_NO_FOUND_EXPLORE_CONFIG = new CardBattleResponseCodeMsg(100043, "副本活动不存在");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_EXPLORE_UPDATE_FAIL = new CardBattleResponseCodeMsg(10044, "更新副本活动失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_ACTIVITY_LOCK_FAIL = new CardBattleResponseCodeMsg(10045, "当前副本未解锁");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_NO_FOUND_BATTLE_EXPLORE_RECORD = new CardBattleResponseCodeMsg(10046, "还未参与过该副本活动");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_NO_FOUND_CAN_OBTAIN_PRIZE = new CardBattleResponseCodeMsg(10047, "暂无可领取的奖励");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_LIMITED_TIME_ACTIVITY_CONFIG_INVALID = new CardBattleResponseCodeMsg(10048, "限时活动奖励配置异常");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_USER_EXPLORE_RECORD_UPDATE_FAIL = new CardBattleResponseCodeMsg(10049, "用户参与副本活动记录更新失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_TODAY_COUNT_UP_LIMIT = new CardBattleResponseCodeMsg(10050, "今日挑战次数达到上限");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_ROLE_GROUP_NOT_ADOPTED_ALL = new CardBattleResponseCodeMsg(10051, "当前角色组角色未全部领养");

    public static final CardBattleResponseCodeMsg CARD_BATTLE_SWEEP_TASK_FAIL = new CardBattleResponseCodeMsg(10052, "战斗关卡扫荡失败");
    public static final CardBattleResponseCodeMsg CARD_BATTLE_BOND_LEVEL_LIMIT = new CardBattleResponseCodeMsg(10053, "关卡羁绊等级限制");

    protected CardBattleResponseCodeMsg(int code, String message) {
        super(code, message);
    }

}
