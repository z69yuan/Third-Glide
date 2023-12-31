package com.bumptech.glide.load.model;

import static com.bumptech.glide.RobolectricConstants.ROBOLECTRIC_SDK;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import android.net.Uri;
import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.util.Preconditions;
import java.io.File;
import java.io.IOException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/** Tests for the {@link UriLoader} class. */
@RunWith(RobolectricTestRunner.class)
@Config(sdk = ROBOLECTRIC_SDK)
public class UriLoaderTest {
  // Not a magic number, just arbitrary non zero.
  private static final int IMAGE_SIDE = 120;

  @Mock private DataFetcher<Object> localUriFetcher;
  @Mock private UriLoader.LocalUriFetcherFactory<Object> factory;
  private UriLoader<Object> loader;
  private Options options;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);

    options = new Options();
    loader = new UriLoader<>(factory);
  }

  @Test
  public void testHandlesFileUris() throws IOException {
    Uri fileUri = Uri.fromFile(new File("f"));
    when(factory.build(eq(fileUri))).thenReturn(localUriFetcher);

    assertTrue(loader.handles(fileUri));
    assertEquals(
        localUriFetcher,
        Preconditions.checkNotNull(loader.buildLoadData(fileUri, IMAGE_SIDE, IMAGE_SIDE, options))
            .fetcher);
  }

  @Test
  public void testHandlesContentUris() {
    Uri contentUri = Uri.parse("content://com.bumptech.glide");
    when(factory.build(eq(contentUri))).thenReturn(localUriFetcher);

    assertTrue(loader.handles(contentUri));
    assertEquals(
        localUriFetcher,
        Preconditions.checkNotNull(
                loader.buildLoadData(contentUri, IMAGE_SIDE, IMAGE_SIDE, options))
            .fetcher);
  }
}
