package December2020;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Problem3 {
    public static void main(String[] args) {
        Map<String, Integer> follower = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();
        while (!line.equals("Log out")) {
            String[] data = line.split(": ");
            String username = data[1];

            switch (data[0]) {
                case "New follower":
                    follower.putIfAbsent(username, 0);
                    break;
                case "Like":
                    int likesCount = Integer.parseInt(data[2]);

                    Integer currentLikes = follower.get(username);
                    if (currentLikes == null) {
                        currentLikes = 0;
                    }
                    follower.put(username, currentLikes + likesCount);
                    break;
                case "Comment":
                    Integer currentComments = follower.get(username);
                    if (currentComments == null) {
                        currentComments = 0;
                    }
                    follower.put(username, currentComments + 1);
                    break;
                case "Blocked":
                    if (follower.containsKey(username)){
                        follower.remove(username);
                    }else {
                        System.out.println(username + " doesn't exist.");
                    }
                    break;
            }
            line = scanner.nextLine();
        }


        System.out.println(follower.size() + " followers");
        follower
                .entrySet()
                .stream()
                .sorted((f1, f2) -> {
                    if (!f1.getValue().equals(f2.getValue())) {
                        return Integer.compare(f2.getValue(), f1.getValue());
                    } else {
                        return f1.getKey().compareTo(f2.getKey());
                    }
                })
                .forEach(f -> System.out.println(f.getKey() + ": " + f.getValue()));
    }
}
/*
New follower: george
Like: george: 5
Comment: george
New follower: george
New follower: jhon
Comment: george
Comment: jhon
Comment: peter
Log out

 */

/*
Like: A: 3
Comment: A
New follower: B
Blocked: A
Comment: B
Like: C: 5
Like: D: 5
Log out

 */