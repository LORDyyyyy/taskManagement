public class Leader extends Person
{
    public Leader(String name, String password)
    {
        super(name, password, "leader");
    }


    /**
     * leader see all request
     * @return all request in 2D array on success,or massage in index[0][0]
     */
    public String[][] allRequest() {
        String[][] all = fs.read("request");
        if (hlp.isEmptyTable(all))
            return all;

        int counter = 0;
        String[][] requests = new String[all.length][all[0].length];
        for (String row[] : all) {
            if (row[4].equals("Under Review")) {
                int count = 0;
                for (String item : row) {
                    requests[counter][count] = item;
                    count++;
                }
            }
            counter++;
        }
        return hlp.removeNullRows(requests);
    }
    
    /** 
    * leader respond to a request
    * @param Id - request id 
    * @param respond - leader respond on request 
    * @return 0 on success
    */
    public boolean respondRequest(String Id, String respond) throws Exception
    {
        if(!respond.toLowerCase().equals("approved") && !respond.toLowerCase().equals("refused"))
            return false;
        else
        {
            fs.update("request",
                hlp.intToArr(0), hlp.paramsToArr(Id)
                ,hlp.intToArr(4), hlp.paramsToArr(respond));
            return true;
        }
    }
}
