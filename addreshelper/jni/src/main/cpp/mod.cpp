#include <jni.h>

extern "C"
JNIEXPORT long JNICALL
Java_uringkt_addresshelper_JNIBridge_getObjectAddress(JNIEnv *env, jclass clazz, jobject obj, long offset) {
    auto ref = env->NewGlobalRef(obj);
    return reinterpret_cast<long>(ref) + offset;
}

extern "C"
JNIEXPORT jobject JNICALL
Java_uringkt_addresshelper_JNIBridge_getObjectFromAddress(JNIEnv *env, jclass clazz, long ptr) {
    auto global = reinterpret_cast<jobject>(ptr);
    auto local = env->NewLocalRef(global);
    env->DeleteGlobalRef(global);
    return local;
}

extern "C"
JNIEXPORT long JNICALL
Java_uringkt_addresshelper_JNIBridge_getByteBufferAddress(JNIEnv *env, jclass clazz, jobject buf) {
    return (long) env->GetDirectBufferAddress(buf);
}