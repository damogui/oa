package org.netsharp.persistence.oql.operation.select;

import org.netsharp.persistence.oql.languangeEngine.items.Operation;

public class ALLOperation extends Operation
    {
        @Override
        public  String getValue()
        {
        	 return "ALL";
        }

        @Override
        public  Boolean isSpace()
        {
        	return true;
        }

        @Override
        public  OperationType getOperationType()
        {
        	return OperationType.Left;
        }
    }
