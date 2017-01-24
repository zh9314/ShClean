import java.io.File;
import java.io.IOException;


public class ShFileClean {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		
		String curDir = new ShFileClean().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		int index = curDir.lastIndexOf('/');
		////This stop file is used to top running this program
		String stopFilePath = curDir.substring(0, index+1)+"stop";
		while(true){
			File fs = new File(args[0]);
			File [] tempFs = fs.listFiles();
			for(int i = 0; i<tempFs.length ; i++)
			{
				String filePath = tempFs[i].getAbsolutePath();
				if(filePath.contains(".sh")){
					File shFile = new File(filePath);
					long createTime = shFile.lastModified();
					java.util.Calendar cal = java.util.Calendar.getInstance();
					long currentMili = cal.getTimeInMillis();
					if((currentMili-createTime) > 3600000){
						Process ps = Runtime.getRuntime().exec("rm "+filePath);  
						ps.waitFor();
					}
				}
			}
			
			File stopFile = new File(stopFilePath);
			if(stopFile.exists())
				break;
			
			Thread.sleep(10000);
		}
	}

}
