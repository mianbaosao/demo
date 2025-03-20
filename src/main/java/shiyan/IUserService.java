package shiyan;

public interface IUserService{
  String register(String name,String password);
  String login(String name,String password);
}
