# rest-webservices

to use Hateous 
#  your entity should extend RepresentationModel<Model>
where Model is the entity extending it.
# use ths
// this get self link
Link selfLink = linkTo(UserController.class).slash(id).withSelfRel();
Link usersLink = linkTo(methodOn(UserController.class)
.getUsers()).withRel("all-users");


Link userLink = null;
Link updateUserLink = null;
for(User u : users){
userLink = linkTo(methodOn(UserController.class)
.getUserswithId(u.getId())).withRel("user");
if(!u.hasLinks()){
u.add(userLink);
}
 for collections
