Function<Room,Room> takeSword = x->{
            if(x.isSword()&&!x.getistaken()){
                System.out.println("--> You have taken sword.");
                return new Room(x.getName(),x.getthingList(),true,false,x.getRoomfrom());
            }else if(x.isSword()&&x.getistaken()){
                System.out.println("--> You already have sword.");
                return new Room(x.getName(),x.getthingList(),x.getRoomfrom());
            }else{
                System.out.println("--> There is no sword.");
                return new Room(x.getName(),x.getthingList(),x.getRoomfrom());
            }
        };

        Function<Room,Room> killTroll = x-> {
            if (!x.isTroll()) {
                System.out.println("--> There is no troll");
                return new Room(x.getName(), x.getthingList(),x.getRoomfrom());
            } else if (x.isTroll() && !x.getistaken()&&!x.getiskilled()) {
                System.out.println("--> You have no sword.");
                return new Room(x.getName(), x.getthingList(),x.getRoomfrom());
            } else if (x.getistaken() && x.isTroll() && !x.getiskilled()&&x.isSword()) {
                System.out.println("--> Troll is killed.");
//                Room returnRoom = new Room(x.getName(), x.getthingList(), true, true,x.getRoomfrom().dropTroll());
//                return returnRoom;
                List<Thing> listWithoutTroll = new ArrayList<>();
        for (Thing thing:x.getthingList()){
            if(thing instanceof Troll){
                //do nothing
            }else{
                listWithoutTroll.add(thing);
            }
        }
                return new Room(x.getName(), listWithoutTroll, true, true,x.getRoomfrom());
//                for(int i=0; i<x.getthingList().size();i++){
//                    if(x.getthingList().get(i) instanceof Troll){
//                        x.getthingList().remove(i);
//                        return new Room(x.getName(), x.getthingList(), true, true,x.getRoomfrom());
//                    }
//                }
                //return new Room(x.getName(), x.getthingList(), true, true,x.getRoomfrom());
            }
            //else if(x.getistaken()&&x.isTroll()&&x.getiskilled()){
            else {
                System.out.println("--> Troll is killed.");
                for(int i=0; i<x.getthingList().size();i++){
                    if(x.getthingList().get(i) instanceof Troll){
                        x.getthingList().remove(i);
                        return new Room(x.getName(), x.getthingList(), true, true,x.getRoomfrom());
                    }
                }
                return new Room(x.getName(), x.getthingList(), true, true,x);
            }

        };
        Function<Room,Room> dropSword = x->{
            System.out.println("--> You have dropped sword.");
            return new Room(x.getName(),x.getthingList(),false,x.getiskilled(),x.getRoomfrom());
        };