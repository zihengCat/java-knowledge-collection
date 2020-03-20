import java.util.*;


public class SolutionN {
    // 状态机当前状态 -> 默认 Q1 初始态
    private static QState currentState = QState.Q1;
    // 状态枚举 -> 支持后续扩展
    enum QState {
        Q1, Q2, Q3,
    }
    // 命令枚举类 -> 支持后续扩展
    private class QCommand {
        public static final String CMD0 = "0";
        public static final String CMD1 = "1";
        // ...
        public final String value;
        private QCommand(String value) {
            this.value = value;
        }
        public String getValue() {
            return this.value;
        }
    }
    /**
     * 
     * @param commands string 字符串一维数组 状态机指令，由“0”、“1”组成
     * @return bool 布尔型
     */
    public boolean handleCommands (String[] commands) {
        if (commands == null || commands.length == 0) {
            return checkState();
        }
        for (String cmd : commands) {
            if (isVaildCommand(cmd)) {
                QCommand tCmd = matchCommand(cmd);
                changeState(tCmd);
            }
            // 异常指令 -> 忽略跳过
        }
        return checkState();
    }
    /**
     * 最终状态检测
     */
    private boolean checkState() {
        return currentState == QState.Q2 ? true : false;
    }
    /**
     * 核心状态切换函数
     *
     * @param cmd
     */
    private void changeState(QCommand cmd) {
        switch (currentState) {
            case Q1:
                processWithQ1(cmd);
                break;
            case Q2:
                processWithQ2(cmd);
                break;
            case Q3:
                processWithQ3(cmd);
                break;
            default:
                break;
        }
    }
    private void processWithQ3(QCommand cmd) {
        switch (cmd.getValue()) {
            case QCommand.CMD0:
                currentState = QState.Q2;
                break;
            case QCommand.CMD1:
                currentState = QState.Q2;
                break;
            // ...
            default:
                break;
        }
    }
    private void processWithQ2(QCommand cmd) {
        switch (cmd.getValue()) {
            case QCommand.CMD0:
                currentState = QState.Q3;
                break;
            case QCommand.CMD1:
                break;
            // ...
            default:
                break;
        }
    }
    private void processWithQ1(QCommand cmd) {
        switch (cmd.getValue()) {
            case QCommand.CMD0:
                break;
            case QCommand.CMD1:
                currentState = QState.Q2;
                break;
            // ...
            default:
                break;
        }
    }
    /**
     * 判断指令是否合法
     */
    private boolean isVaildCommand(String cmd) {
        return (cmd != null)
            && (cmd.equals("0") || cmd.equals("1"));
    }
    /**
     * 匹配命令。
     *
     * @param cmd
     * @return
     */
    private QCommand matchCommand(String cmd) {
        switch (cmd) {
            case "0": return new QCommand(cmd);
            case "1": return new QCommand(cmd);
            // ...
            default : return null;
        }
    }
}
/* EOF */