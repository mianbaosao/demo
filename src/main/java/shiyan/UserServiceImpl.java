package shiyan;

public class UserServiceImpl implements IUserService{
  public String register(String name,String password){
      System.out.println("UserServiceImpl register()...”]");
      return "注册成功";
}
public String login(String name,String password){
      System.out.println("UserServiceImpl login()...");
      return "登录成功";
}
}
