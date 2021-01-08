package MoreExercise;

import java.util.*;

public class TeamworkProjects {
    private static class Project {
        String teamCreator;
        String teamName;
        List<String> teamMembers;

        Project(String teamCreator, String teamName) {
            this.teamCreator = teamCreator;
            this.teamName = teamName;
            this.teamMembers = new ArrayList<>();
        }

        private String getTeamCreator() {
            return this.teamCreator;
        }

        private String getTeamName() {
            return this.teamName;
        }

        private List<String> getTeamMembers() {
            return this.teamMembers;
        }

        private void addMember(String name) {
            this.teamMembers.add(name);
        }
    }

    public static void main(String[] args) {
        List<Project> projectList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            String[] userAndTeam = scanner.nextLine().split("-");
            String user = userAndTeam[0];
            String team = userAndTeam[1];

            boolean projectAlreadyExist = false;
            for (Project project : projectList) {
                if (project.getTeamName().equals(team)) {
                    projectAlreadyExist = true;
                    break;
                }
            }

            boolean hasATeam = false;
            for (Project project : projectList) {
                if (project.getTeamCreator().equals(user)) {
                    hasATeam = true;
                    break;
                }
            }
            if (projectAlreadyExist) {
                System.out.println("Team " + team + " was already created!");
            } else if (hasATeam) {
                System.out.println(user + " cannot create another team!");
            } else {
                Project p = new Project(user, team);
                projectList.add(p);
                System.out.println("Team " + team + " has been created by " + user + "!");
            }
        }

        String actions;
        while (!"end of assignment".equals(actions = scanner.nextLine())) {
            String[] userAndTeam = actions.split("->");
            String user = userAndTeam[0];
            String team = userAndTeam[1];

            boolean existingTeam = false;
            for (Project project : projectList) {
                if (project.getTeamName().equals(team)) {
                    existingTeam = true;
                    break;
                }
            }

            boolean isAlreadyInATeam = false;
            for (Project project : projectList) {
                List<String> members = project.getTeamMembers();
                if (project.getTeamCreator().equals(user) || isAlreadyInATeam) {
                    isAlreadyInATeam = true;
                    break;
                }
                for (String member : members) {
                    if (member.equals(user)) {
                        isAlreadyInATeam = true;
                        break;
                    }
                }
            }
            if (!existingTeam) {
                System.out.println("Team " + team + " does not exist!");
            } else if (isAlreadyInATeam) {
                System.out.println("Member " + user + " cannot join team " + team + "!");
            } else {
                for (Project project : projectList) {
                    if (project.getTeamName().equals(team)) {
                        project.addMember(user);
                    }
                }
            }
        }
        projectList.sort((p1, p2) -> {
            int result = Integer.compare(p2.getTeamMembers().size(), p1.getTeamMembers().size());
            if (result == 0) {
                result = p1.getTeamName().compareTo(p2.getTeamName());
            }
            return result;
        });

        for (Project project : projectList) {
            Collections.sort(project.getTeamMembers());
            if (!project.getTeamMembers().isEmpty()) {
                System.out.println(project.getTeamName());
                System.out.println("- " + project.getTeamCreator());
                for (String member : project.getTeamMembers()) {
                    System.out.println("-- " + member);
                }
            }
        }
        System.out.println("Teams to disband:");
        for (Project project : projectList) {
            if (project.getTeamMembers().isEmpty()) {
                System.out.println(project.getTeamName());
            }
        }
    }
}
