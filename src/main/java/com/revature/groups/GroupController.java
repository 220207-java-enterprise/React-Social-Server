package com.revature.groups;

import com.revature.groups.dtos.GroupCreationRequest;
import com.revature.groups.dtos.GroupResponse;
import com.revature.groups.dtos.GroupUpdateRequest;
import com.revature.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/group")
public class GroupController {

    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    // Get all groups
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(produces = "application/json")
    public List<GroupResponse> getAllGroups() {
        return groupService.getAllGroups();
    }

    //Get One Group
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{groupName}")
    public GroupResponse getOneGroup(@PathVariable String groupName) {
        return groupService.getGroup(groupName);
    }

    // Create Group
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = "application/json")
    public void createGroup(
            @RequestBody GroupCreationRequest groupCreationRequest,
            @AuthenticationPrincipal User owner) {
        groupService.createGroup(groupCreationRequest, owner);
    }

    // Join Group
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/join/{groupName}")
    public void joinGroup(@PathVariable String groupName, @AuthenticationPrincipal User currUser) {
        groupService.joinGroup(groupName, currUser);
    }

    // Leave Group
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/leave/{groupName}")
    public void leaveGroup(@PathVariable String groupName, @AuthenticationPrincipal User currUser) {
        groupService.leaveGroup(groupName, currUser);
    }

    // update Group
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping(name = "/update/{groupName}", consumes = "application/json")
    public void updateGroup(
            @PathVariable String groupName,
            @RequestBody GroupUpdateRequest updateReq,
            @AuthenticationPrincipal User currUser) {
        groupService.updateGroup(groupName, updateReq, currUser);
    }

    // delete group
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{groupName}")
    public void deleteGroup(@PathVariable String groupName, @AuthenticationPrincipal User currUser) {
        groupService.deleteGroup(groupName, currUser);
    }


}