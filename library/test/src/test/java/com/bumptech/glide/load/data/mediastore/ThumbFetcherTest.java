package com.bumptech.glide.load.data.mediastore;

import static com.bumptech.glide.RobolectricConstants.ROBOLECTRIC_SDK;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.net.Uri;
import android.provider.MediaStore;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.InputStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = ROBOLECTRIC_SDK)
public class ThumbFetcherTest {

  @Mock private ThumbnailStreamOpener opener;
  @Mock private DataFetcher.DataCallback<InputStream> callback;
  @Mock private InputStream expected;

  private ThumbFetcher fetcher;
  private Uri uri;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);

    uri = Uri.withAppendedPath(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, "123");
    fetcher = new ThumbFetcher(uri, opener);
  }

  @Test
  public void testReturnsInputStreamFromThumbnailOpener() throws Exception {
    when(opener.open(eq(uri))).thenReturn(expected);

    fetcher.loadData(Priority.LOW, callback);
    verify(callback).onDataReady(ArgumentMatchers.<InputStream>isNotNull());
  }

  @Test
  public void testClosesInputStreamFromThumbnailOpenerOnCleanup() throws Exception {
    when(opener.open(eq(uri))).thenReturn(expected);

    fetcher.loadData(Priority.HIGH, callback);

    fetcher.cleanup();
    verify(expected).close();
  }

  @Test
  public void testDoesNotThrowIfCleanupWithNullInputStream() {
    fetcher.cleanup();
  }
}
