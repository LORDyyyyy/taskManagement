// public class UserAuthentication 
// {
//     private FileStorage fileStorage;

//     public UserAuthentication(FileStorage fileStorage) 
//     {
//         this.fileStorage = fileStorage;
//     }

//     public boolean login(String username, String password)
//     {
//         String[][] userData = fileStorage.read("user_credentials_table");
//         for (String[] user : userData) 
//         {
//             if (user[0].equals(username) && user[1].equals(password))
//             {
//                 return true; 
//             }
//         }
//         return false; 
//     }

    
// }
