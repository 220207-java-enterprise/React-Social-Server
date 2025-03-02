package com.revature.groups.dtos;

import com.revature.groups.Group;
import com.revature.users.dtos.UserDto;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GroupResponse {

    private String groupID;
    private UserDto owner;
    private String name;
    private String description;
    private String headerImg;
    private String profilePic;
    private List<UserDto> joinedUsers;

    public GroupResponse(Group group) {
        this.groupID = group.getId().toString();
        this.owner = new UserDto(group.getOwner());
        this.name = group.getName();
        this.description = group.getDescription();
        this.headerImg = group.getHeaderImg();
        this.profilePic = group.getProfilePic();
        this.joinedUsers = group.getUsers()
                .stream()
                .map(UserDto::new)
                .collect(Collectors.toList());
    }
}
