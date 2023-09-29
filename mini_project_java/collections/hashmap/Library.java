package mini_project_java.collections.hashmap;

import java.util.*;

class Library
{

    String bookName;
    String author;
    Library()
    {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.bookName);
        hash = 83 * hash + Objects.hashCode(this.author);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Library other = (Library) obj;
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        return true;
    }


    Library(String bookName,String author)
    {
        this.bookName=bookName;
        this.author=author;
    }
    public HashMap<Integer,Library> createLibraryMap(String booksInLibrary)
    {
        HashMap<Integer,Library> bookIdsMap = new HashMap<>();
        String Ids[] = booksInLibrary.split("\\|");

        for(String s1: Ids){
            String parts[] = s1.split(",");
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0]);
                String bookName = parts[1];
                String author = parts[2];

                Library libraryObject = new Library(bookName, author);
                bookIdsMap.put(id, libraryObject);
            }
        }
        return bookIdsMap;

    }
    public HashMap<Integer,Integer> createUserMap(String borrowedUsers)
    {

        HashMap<Integer,Integer> bookIdVsUserId = new HashMap<>();
        String Ids[] = borrowedUsers.split("\\|");

        for(String s1: Ids){
            String ids[] = s1.split(",");
            if(ids.length==2){
                Integer bId = Integer.valueOf(ids[0]);
                Integer uId = Integer.valueOf(ids[1]);
                bookIdVsUserId.put(bId, uId);
            }
        }
        return bookIdVsUserId;
    }


    public String getQuery(String booksInLibrary,String borrowedUsers,String query)
    {

        StringBuilder ans = new StringBuilder();
        HashMap<Integer,Integer> bookIdVsUser = createUserMap(borrowedUsers);
        HashMap<Integer,Library> bookidVsObj = createLibraryMap(booksInLibrary);

        String queryParams[] = query.split(",");
        Integer queryName = Integer.valueOf(queryParams[0]);

        switch(queryName){
            case 1:{
                Integer bookId = Integer.valueOf(queryParams[1]);
                if(bookIdVsUser.containsKey(bookId)){
                    ans.append("No Stock\nIt is owned by "+bookIdVsUser.get(bookId)+"\n");
                }
                else{
                    ans.append("It is available\nAuthor is " + bookidVsObj.get(bookId).author+"\n");
                }
                return ans.toString();
            }
            case 2: {
                Integer userId = Integer.valueOf(queryParams[1]);
                //get List bookId from given userID
                ArrayList<Integer> bookIds = new ArrayList<>();
                for(Map.Entry<Integer,Integer> entry: bookIdVsUser.entrySet()){
                    if(entry.getValue().equals(userId)){
                        bookIds.add(entry.getKey());
                    }
                }
                for(Integer bookId : bookIds){
                    ans.append(bookId + " " + bookidVsObj.get(bookId).bookName+"\n");
                }
                System.out.println(ans.toString());
                return ans.toString();
            }

            case 3:{
                String bookName = (queryParams[1]);
                ArrayList<Integer> ids = new ArrayList<>();
                for(Map.Entry<Integer,Library> entry: bookidVsObj.entrySet()){
                    if(entry.getValue().bookName.equals(bookName)){
                        ids.add(entry.getKey());
                    }
                }

                Integer countOut =0;
                for(Integer id: ids){
                    if(bookIdVsUser.containsKey(id)){
                        countOut++;
                    }
                }
                Integer bookIn = ids.size() - countOut.intValue();
                ans.append(countOut.toString() +" out\n"+ String.valueOf(bookIn) +" in\n");
                System.out.println(ans.toString());
                return ans.toString();
            }
            case 4:{
                String author = (queryParams[1]);
                for(Map.Entry<Integer,Library> entry: bookidVsObj.entrySet()){
                    Library lib = entry.getValue();
                    if(lib.author.equals(author)){
                        ans.append(lib.bookName+"\n");
                    }
                }
                System.out.println(ans.toString());
                return ans.toString();
            }
            case 5:{
                String search = (queryParams[1]);
                for(Map.Entry<Integer,Library> entry: bookidVsObj.entrySet()){
                    Library lib = entry.getValue();
                    if(lib.bookName.toLowerCase().contains(search)){
                        ans.append(entry.getKey() + " " +lib.bookName+"\n");
                    }
                }
                System.out.println(ans.toString());
                return ans.toString();
            }
            default : return null;
        }

    }

}

