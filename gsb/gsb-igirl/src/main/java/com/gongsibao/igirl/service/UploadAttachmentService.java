package com.gongsibao.igirl.service;
import com.gongsibao.bd.service.GsbPersistableService;
import com.gongsibao.entity.igirl.TradeMark;
import com.gongsibao.entity.igirl.TradeMarkCase;
import com.gongsibao.entity.igirl.UploadAttachment;
import com.gongsibao.entity.igirl.dict.AttachmentCat;
import com.gongsibao.entity.igirl.dict.FileType;
import com.gongsibao.entity.igirl.dict.ShareGroup;
import com.gongsibao.igirl.base.IUploadAttachmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.netsharp.communication.Service;
@Service
public class UploadAttachmentService extends GsbPersistableService<UploadAttachment> implements IUploadAttachmentService {

	//附件营业执照商标ID赋值为-1，因为多个商标共享
	public final static Integer TradeMarkBizLienseID = -1;
		//付款证明商标ID为赋予值为-2，因为多个商标共享
	public final static Integer TradeMarkPayProofID = -2;
	public UploadAttachmentService() {
		super();
		this.type = UploadAttachment.class;
	}




   

}