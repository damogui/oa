package com.gongsibao.igirl.tm.service.builder;

import java.util.ArrayList;
import java.util.List;

import com.gongsibao.entity.igirl.tm.DownloadAttachment;
import com.gongsibao.entity.igirl.tm.TradeMark;
import com.gongsibao.entity.igirl.tm.TradeMarkCase;
import com.gongsibao.entity.igirl.tm.UploadAttachment;
import com.gongsibao.entity.igirl.tm.dict.AttachmentCat;
import com.gongsibao.entity.igirl.tm.dict.FileType;
import com.gongsibao.igirl.tm.service.builder.base.AttachmentBuilderManager;
import com.gongsibao.igirl.tm.service.builder.base.IAttachmentBuilder;
/**
 * 
 * @author jy
 * -------------上传
 *负责创建当前商标共享组对应的基本附件集合
 *黑白商标图样
 *委托书
 *其它证明
 *--------------下载
 *黑白委托书
 *黑白确认函
 */
public class CommonAttachmentBuilder implements IAttachmentBuilder {
	public static final String deleProofName="_黑白委托书";
	@Override
	public List<UploadAttachment> buildUploads(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<UploadAttachment> ups=new ArrayList<UploadAttachment>();
		UploadAttachment attachment1 = AttachmentBuilderManager.buildUploadAttachment(tm.getMemo() + "_黑白商标图样",
				AttachmentCat.TRADEMARK_PICT, tm.getTradeMarkCaseId(), FileType.JPGB, FileType.JPGB, tm.getId(),tm.getShareGroup());
		ups.add(attachment1);

		attachment1 =  AttachmentBuilderManager.buildUploadAttachment(tm.getMemo() + "_黑白委托书",
				AttachmentCat.DELEGATE_PROOF, tm.getTradeMarkCaseId(), FileType.JPGB, FileType.JPGB, tm.getId(),tm.getShareGroup());
		ups.add(attachment1);

		attachment1 = AttachmentBuilderManager.buildUploadAttachment(tm.getMemo() + "_补充证明", AttachmentCat.MEMO_DESC,
				tm.getTradeMarkCaseId(), FileType.JPGC, FileType.JPGC, tm.getId(),tm.getShareGroup());
		// 默认设置为不需要传
		attachment1.setNeeded(false);
		ups.add(attachment1);
		
		return ups;
	}

	@Override
	public List<DownloadAttachment> buildDownloads(TradeMark tm,TradeMarkCase tmc) {
		// TODO Auto-generated method stub
		List<DownloadAttachment> ds=new ArrayList<DownloadAttachment>();
		DownloadAttachment attachment1 =AttachmentBuilderManager.buildDownloadAttachment(tm.getMemo() + "_黑白委托书",
				AttachmentCat.DELEGATE_PROOF, tm.getTradeMarkCaseId(), FileType.JPGB, FileType.JPGB, tm.getId(),tm.getShareGroup());
		attachment1.setNeeded(true);
		ds.add(attachment1);
		
		 attachment1 =AttachmentBuilderManager.buildDownloadAttachment(tm.getMemo() + "_黑白确认函",
					AttachmentCat.CONFIRM_PROOF, tm.getTradeMarkCaseId(), FileType.JPGB, FileType.JPGB, tm.getId(),tm.getShareGroup());
		 attachment1.setNeeded(false);
		ds.add(attachment1);
		
		return ds;
	}

}
