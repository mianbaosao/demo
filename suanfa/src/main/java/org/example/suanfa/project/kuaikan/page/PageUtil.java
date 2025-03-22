package org.example.suanfa.project.kuaikan.page;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.lang.NonNull;

public class PageUtil {

  @NonNull
  public static <T> Pair<List<T>, Integer> slice(@NonNull List<T> source, int since, int offset) {
    if (since < 0 || offset < 0) {
      // 简单起见，offset 不能为负数
      throw new IllegalArgumentException(
          ("since or offset can not be negative, since = " + since + ", offset = " + offset));
    }
    int newSince;
    int toIndex = Math.min(source.size(), since + offset);
    if (toIndex >= source.size()) {
      newSince = -1;
    } else {
      newSince = toIndex;
    }

    List<T> slicedSource = source.stream().skip(since).limit(offset).collect(Collectors.toList());

    return Pair.of(slicedSource, newSince);
  }

  // 获取分页数据
  public static <T> List<T> getListByPage(List<T> list, int offset, int length) {
    if (CollectionUtils.isEmpty(list)) {
      return Collections.emptyList();
    }
    int size = list.size();
    if (offset < 0 || length <= 0 || size <= offset) {
      return Collections.emptyList();
    }
    int count = 0;
    List<T> result = new ArrayList<T>();
    for (int i = offset; i < size; i++) {
      result.add(list.get(i));
      count++;
      if (count == length) {
        break;
      }
    }
    return result;
  }

  public static boolean hasMore(int offset, int length, int totalCount) {
    if (offset < 0 || length <= 0 || totalCount <= 0) {
      return false;
    }
    return offset + length < totalCount;
  }

  public static int getTotalPage(int count, int pageSize) {
    int totalPage = 0;
    if (count < 0) {
      return totalPage;
    }

    if (count % pageSize == 0) {
      totalPage = count / pageSize;
    } else {
      totalPage = count / pageSize + 1;
    }
    return totalPage;
  }

  public static int getTotalPage(int count) {
    return getTotalPage(count, CommonConstant.PAGESIZE);
  }
}
