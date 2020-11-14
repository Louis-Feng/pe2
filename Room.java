import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Room{
    private final String  name;
    private final List<Thing> thingList;
    private final boolean istaken;
    private final boolean iskilled;
    private final Room roomfrom;
    public Room(String name){
        this.name = name;
        this.thingList = new ArrayList<Thing>();
        this.istaken=false;
        this.iskilled = false;
        this.roomfrom = null;
    
    }
    Room (String name, List<Thing> thingList,Room roomfrom){
        this.name = name;
        this.istaken=false;
        this.iskilled = false;
        this.thingList = thingList;
        this.roomfrom = roomfrom;
    }
    Room (String name, List<Thing> thingList, Thing thing,Room roomfrom){
        this.name = name;
        this.istaken=false;
        this.iskilled = false;
        thingList.add(thing);
        this.thingList = thingList;

        this.roomfrom = roomfrom;
    }
    Room (String name, List<Thing> thingList){
        this.name = name;
        this.istaken=false;
        this.iskilled = false;
        this.thingList = thingList;
        this.roomfrom = this;
    }
    Room (String name, List<Thing> thingList, boolean istaken, boolean iskilled,Room roomfrom){
        this.name = name;
        this.thingList = thingList;
        this.istaken = istaken;
        this.iskilled = iskilled;
        this.roomfrom = roomfrom;
    }
    Room (String name, Room roomfrom){
        this.name = name;
        this.thingList = roomfrom.getthingList();
        this.istaken = roomfrom.getistaken();
        this.iskilled = false;
        this.roomfrom = roomfrom;
    }
    public Room add(Thing thing){
        List<Thing> newthingList = new ArrayList<>();
        for(int i=0;i<this.thingList.size();i++){
            newthingList.add(this.thingList.get(i));
        }
        return new Room(this.name,newthingList,thing,this.getRoomfrom());
        }
    public Room tick(){
        return new Room(this.name, thingList.stream().map(x->x.tick()).collect(Collectors.toList()),this.istaken,this.iskilled,this.getRoomfrom());
    }
    public Room tick(Function<Room, Room> function){
        Room newRoom = function.apply(this);
        return  newRoom.tick();
        //Room newRoom = this.tick();
        //return function.apply(newRoom);
    }
    public Room go(Function<Room, Room> function){
        Room roomafterfunction = function.apply(this);
        Room nextRoom = new Room(roomafterfunction.getName(),roomafterfunction.getthingList(),roomafterfunction.getistaken(),roomafterfunction.getiskilled(),this);
        if(this.istaken&&!nextRoom.getistaken()){
            Room newRoom = new Room(roomafterfunction.getName(),roomafterfunction.getthingList());
            newRoom.getthingList().add(0,new Sword());
//            for(int i=0; i<this.getthingList().size();i++){
//                if(this.getthingList().get(i) instanceof Troll){
//                    this.getthingList().remove(i);
//                }
//            }
            return new Room(newRoom.getName(),newRoom.getthingList(),true,false,this.dropSword());
        }
        else{return nextRoom;}

//        if(this.istaken){
//            int swordIndex;
//            for(int k=0;k<thingList.size();k++){
//                if(thingList.get(k) instanceof Sword){
//                    swordIndex = k;
//                }
//            }
//            Room newRoom = function.apply(this.thingList);
//            newRoom.getthingList().add(0,new Sword());
//            this.thingList.remove(swordIndex); //takesword 要go别的房间的时候，在原房间要删掉
//            return new Room(newRoom.getName(),newRoom.getthingList(),true,newRoom.getiskilled(),this);
//
//        }
//        else{
//
//
//        Room newRoom = function.apply(this.thingList);
//        Room newnewRoom = new Room(newRoom.getName(),newRoom.getthingList(),this);
//        return newnewRoom;}
    }
    public Room back(){
            Room toBack = this.roomfrom;
            Room backAlready = new Room(toBack.getName(),toBack.getthingList(),this.getistaken(),toBack.getiskilled(),toBack.getRoomfrom());
        if(backAlready.istaken){
            backAlready.getthingList().add(new Sword());
            return backAlready.tick();

        }
        else{
            return backAlready.tick();


            }



    }


public Room dropSword(){
    List<Thing> listWithoutSword = new ArrayList<>();
    for (Thing thing:this.thingList){
        if(thing instanceof Sword){
            //do nothing
        }else{
            listWithoutSword.add(thing);
        }
    }
    return new Room(this.name,listWithoutSword,false,this.iskilled,this.getRoomfrom());
}
    public Room getRoomfrom(){return this.roomfrom;}
    public boolean getistaken() {
        return this.istaken;
    }
    public boolean getiskilled(){return this.iskilled;}

    public String getName() {
        return this.name;
    }
    public List<Thing> getthingList() {
        return this.thingList;
    }
    public boolean isSword(){
        boolean isSword = false;
        for(int j=0;j<thingList.size();j++){
            if(thingList.get(j) instanceof Sword){
                isSword = true;
            }
        }
        return isSword;
    }
    public boolean isTroll(){
        boolean isTroll = false;
        for(int j=0;j<thingList.size();j++){
            if(thingList.get(j) instanceof Troll){
                isTroll = true;
            }
        }
        return isTroll;
    }

    @Override
    public String toString(){
        String printalble = "";
        for(int i = 0; i<thingList.size(); i++){
            printalble = printalble + "\n" + thingList.get(i).toString();
        }
        return "@" + this.name + printalble;

    }


}
