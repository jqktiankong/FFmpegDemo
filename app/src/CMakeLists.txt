cmake_minimum_required(VERSION 3.10.2)

project("ffmpegdemo")

#获取上级目录的路径.
get_filename_component(PARENT_DIR ${CMAKE_SOURCE_DIR} PATH)

add_library(
        ffmpeg-demo

        SHARED
        main/cpp/ffmpeg_hello.c
)

#add libavcodec
add_library(libavcodec
        SHARED
        IMPORTED
        )

set_target_properties(
        libavcodec
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libavcodec.so
)

##add libavdevice
#add_library(libavdevice
#        SHARED
#        IMPORTED
#        )
#
#set_target_properties(
#        libavdevice
#        PROPERTIES IMPORTED_LOCATION
#        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libavdevice.so
#)

#add libavfilter
add_library(libavfilter
        SHARED
        IMPORTED
        )

set_target_properties(
        libavfilter
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libavfilter.so
)


#add libavformat
add_library(libavformat
        SHARED
        IMPORTED
        )

set_target_properties(
        libavformat
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libavformat.so
)


#add libavutil
add_library(libavutil
        SHARED
        IMPORTED
        )

set_target_properties(
        libavutil
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libavutil.so
)


##add libpostproc
#add_library(libpostproc
#        SHARED
#        IMPORTED
#        )
#
#set_target_properties(
#        libpostproc
#        PROPERTIES IMPORTED_LOCATION
#        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libpostproc.so
#)

#add libswresample
add_library(libswresample
        SHARED
        IMPORTED
        )

set_target_properties(
        libswresample
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libswresample.so
)

#add libswscale
add_library(libswscale
        SHARED
        IMPORTED
        )

set_target_properties(
        libswscale
        PROPERTIES IMPORTED_LOCATION
        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libswscale.so
)

##add libavresample
#add_library(libavresample
#        SHARED
#        IMPORTED
#        )
#
#set_target_properties(
#        libavresample
#        PROPERTIES IMPORTED_LOCATION
#        ${PARENT_DIR}/libs/${ANDROID_ABI}/lib/libavresample.so
#)

#头文件
include_directories(
        ${PARENT_DIR}/libs/${ANDROID_ABI}/include
        main/cpp)

find_library(
        log-lib
        log)

target_link_libraries(
        ffmpeg-demo

        libavcodec
#        libavdevice
        libavfilter
        libavformat
        libavutil
#        libpostproc
        libswresample
        libswscale
#        libavresample

        ${log-lib})