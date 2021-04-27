package com.czg.xmind;

import com.czg.xmind.bean.XMindNode;
import org.xmind.core.ITopic;

public interface NodeFactory {

    XMindNode createNode(ITopic topic);
}