cmake_minimum_required(VERSION 3.10.2)

project("ffmpegdemo")

#获取上级目录的路径.
get_filename_component(PARENT_DIR ${CMAKE_SOURCE_DIR} PATH)

add_library(
        ffmpeg-demo

        SHARED
        src/main/cpp/ffmpeg_hello.c
)

#add libavcodec
add_library(libavcodec
        SHARED
        IMPORTED
        )

set_target_properties(
        libavcodec
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/ffmpeglibs/${ANDROID_ABI}/lib/libavcodec.so
)

#add libavfilter
add_library(libavfilter
        SHARED
        IMPORTED
        )

set_target_properties(
        libavfilter
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/ffmpeglibs/${ANDROID_ABI}/lib/libavfilter.so
)


#add libavformat
add_library(libavformat
        SHARED
        IMPORTED
        )

set_target_properties(
        libavformat
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/ffmpeglibs/${ANDROID_ABI}/lib/libavformat.so
)


#add libavutil
add_library(libavutil
        SHARED
        IMPORTED
        )

set_target_properties(
        libavutil
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/ffmpeglibs/${ANDROID_ABI}/lib/libavutil.so
)

#add libswresample
add_library(libswresample
        SHARED
        IMPORTED
        )

set_target_properties(
        libswresample
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/ffmpeglibs/${ANDROID_ABI}/lib/libswresample.so
)

#add libswscale
add_library(libswscale
        SHARED
        IMPORTED
        )

set_target_properties(
        libswscale
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/ffmpeglibs/${ANDROID_ABI}/lib/libswscale.so
)

#头文件
include_directories(
        ${PARENT_DIR}/ffmpeglibs/${ANDROID_ABI}/include
        src/main/cpp)

find_library(
        log-lib
        log)

target_link_libraries(
        ffmpeg-demo

        libavcodec
        libavfilter
        libavformat
        libavutil
        libswresample
        libswscale

        ${log-lib})