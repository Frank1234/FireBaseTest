package com.ironflowers.firebasetest.data.control;

import com.ironflowers.firebasetest.data.model.ContentItem;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.ironflowers.firebasetest.util.MyTestUtils.createFakeContentItem;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class ContentItemControllerTest {

    ContentItemController contentItemController;
    List<ContentItem> testItems;

    @Before
    public void setUp() {

        contentItemController = new ContentItemController();

        testItems = Arrays.asList(
                createFakeContentItem(1),
                createFakeContentItem(2),
                createFakeContentItem(3),
                createFakeContentItem(4));
    }

    @Test
    public void findItemForId_ManyFound() {
        assertEquals(contentItemController.findItemForId(testItems, 1), testItems.get(0));
        assertEquals(contentItemController.findItemForId(testItems, 2), testItems.get(1));
        assertEquals(contentItemController.findItemForId(testItems, 4), testItems.get(3));
    }

    @Test
    public void findItemForId_ManyNotFound() {
        assertNull(contentItemController.findItemForId(testItems, 0));
        assertNull(contentItemController.findItemForId(testItems, -1));
        assertNull(contentItemController.findItemForId(testItems, 5));
    }
}
