package jp.utokyo.shibalab.facebookarchiveparser.like;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Like2Csv {
	/* ==============================================================
	 * static methods
	 * ============================================================== */
	/**
	 * entry point
	 * @param args 0: message root folder path, 1: output file path
	 */
	public static void main(String[] args) {
		File likeRootDir = new File(args[0]);
		File outputDir   = new File(args[1]);
		
		
		LikeParser parser = new LikeParser();
		
		List<PostLike>  postLikes  = parser.parsePosts( new File(likeRootDir, LikeParser.FILE_LIKE_POSTS_AND_COMMENTS));
		List<PageLike>  pageLikes  = parser.parsePages( new File(likeRootDir, LikeParser.FILE_LIKE_PAGES));
		List<OtherLike> otherLikes = parser.parseOthers(new File(likeRootDir, LikeParser.FILE_LIKE_EXTERNAL_SITES));
		
		try (
			BufferedWriter bw1=Files.newBufferedWriter(new File(outputDir, "facebook_like_posts.csv" ).toPath());
			BufferedWriter bw2=Files.newBufferedWriter(new File(outputDir, "facebook_like_pages.csv" ).toPath());
			BufferedWriter bw3=Files.newBufferedWriter(new File(outputDir, "facebook_like_others.csv").toPath());
		) {
			// export like posts //////////////////////////
			System.out.println("start exporting like posts ... ");
			bw1.write(PostLike.getCsvHeader("\t"));
			bw1.newLine();
			for(PostLike postLike:postLikes) {
				System.out.println("\t\t" + postLike.getTitle());
				
				bw1.write( postLike.toCsvString("\t") );
				bw1.newLine();
			}
			System.out.println("... done");
			

			// export like pages //////////////////////////
			System.out.println("start exporting like pages ... ");
			bw2.write(PageLike.getCsvHeader("\t"));
			bw2.newLine();
			for(PageLike pageLike:pageLikes) {
				System.out.println("\t\t" + pageLike.getName());
				
				bw2.write( pageLike.toCsvString("\t") );
				bw2.newLine();
			}
			System.out.println("... done");			
			
			
			// export like pages //////////////////////////
			System.out.println("start exporting like others ... ");
			bw3.write(OtherLike.getCsvHeader("\t"));
			bw3.newLine();
			for(OtherLike otherLike:otherLikes) {
				System.out.println("\t\t" + otherLike.getTitle());
				
				bw3.write( otherLike.toCsvString("\t") );
				bw3.newLine();
			}
			System.out.println("... done");			
		}
		catch(IOException exp) {
			exp.printStackTrace();
		}
	}

}
