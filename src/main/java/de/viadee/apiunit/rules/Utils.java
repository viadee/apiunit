package de.viadee.apiunit.rules;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaMethod;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Utils {

    public static boolean isPatchMethod(JavaMethod method) {
        if (method.isAnnotatedWith(PatchMapping.class)) {
            return true;
        } else {
            return checkRequestMappingForMethodType(method, RequestMethod.PATCH);
        }
    }

    public static boolean isPutMethod(JavaMethod method) {
        if (method.isAnnotatedWith(PutMapping.class)) {
            return true;
        } else {
            return checkRequestMappingForMethodType(method, RequestMethod.PUT);
        }
    }

    public static boolean isPostMethod(JavaMethod method) {
        if (method.isAnnotatedWith(PostMapping.class)) {
            return true;
        } else {
            return checkRequestMappingForMethodType(method, RequestMethod.POST);
        }
    }

    public static boolean isDeleteMethod(JavaMethod method) {
        if (method.isAnnotatedWith(DeleteMapping.class)) {
            return true;
        } else {
            return checkRequestMappingForMethodType(method, RequestMethod.DELETE);
        }
    }

    public static boolean isGetMethod(JavaMethod method) {
        if (method.isAnnotatedWith(GetMapping.class)) {
            return true;
        } else {
            return checkRequestMappingForMethodType(method, RequestMethod.GET);
        }
    }

    private static boolean checkRequestMappingForMethodType(JavaMethod method, RequestMethod methodType){
        Optional<RequestMapping> optionalRequestMapping = method.tryGetAnnotationOfType(RequestMapping.class);
        if (optionalRequestMapping.isPresent()) {
            for (RequestMethod requestMethod : optionalRequestMapping.get().method()) {
                if (requestMethod == methodType) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isHttpMethod(JavaMethod method) {
        if (method.isAnnotatedWith(RequestMapping.class)) {
            return true;
        } else if (method.isAnnotatedWith(GetMapping.class)) {
            return true;
        } else if (method.isAnnotatedWith(PostMapping.class)) {
            return true;
        } else if (method.isAnnotatedWith(PutMapping.class)) {
            return true;
        } else if (method.isAnnotatedWith(DeleteMapping.class)) {
            return true;
        } else if (method.isAnnotatedWith(PatchMapping.class)) {
            return true;
        }
        return false;
    }

    public static String getCollectionId(JavaMethod method) {
        String[] tmp = new String[]{""};
        if (method.isAnnotatedWith(RequestMapping.class)) {
            tmp = getURLFromRequestMappingMethod(method);
        } else if (method.isAnnotatedWith(GetMapping.class)) {
            tmp = getURLFromGetMappingMethod(method);
        } else if (method.isAnnotatedWith(PostMapping.class)) {
            tmp = getURLFromPostMappingMethod(method);
        } else if (method.isAnnotatedWith(PutMapping.class)) {
            tmp = getURLFromPutMappingMethod(method);
        } else if (method.isAnnotatedWith(DeleteMapping.class)) {
            tmp = getURLFromDeleteMappingMethod(method);
        } else if (method.isAnnotatedWith(PatchMapping.class)) {
            tmp = getURLFromPatchMappingMethod(method);
        }
        for (String s : tmp) {
            String[] splittedURL = s.split("/");
            for (int y = splittedURL.length - 1; y > 0; y--) {
                if (!isPathParam(splittedURL[y])) {
                    return splittedURL[y];
                }
            }
        }
        return "";
    }

    private static String[] getURLFromRequestMappingMethod(JavaMethod method) {
        Optional<RequestMapping> opt = method.tryGetAnnotationOfType(RequestMapping.class);
        if (opt.isPresent()) {
            String[] methodURLs = getValuesOrPaths(opt.get().value(), opt.get().path());
            return concatClassUrlWithMethodUrl(method, methodURLs);
        }
        return new String[]{""};
    }

    private static String[] getURLFromGetMappingMethod(JavaMethod method) {
        Optional<GetMapping> opt = method.tryGetAnnotationOfType(GetMapping.class);
        if (opt.isPresent()) {
            String[] methodURLs = getValuesOrPaths(opt.get().value(), opt.get().path());
            return concatClassUrlWithMethodUrl(method, methodURLs);
        }
        return new String[]{""};
    }


    private static String[] getURLFromPostMappingMethod(JavaMethod method) {
        Optional<PostMapping> opt = method.tryGetAnnotationOfType(PostMapping.class);
        if (opt.isPresent()) {
            String[] methodURLs = getValuesOrPaths(opt.get().value(), opt.get().path());
            return concatClassUrlWithMethodUrl(method, methodURLs);
        }
        return new String[]{""};
    }

    private static String[] getURLFromPutMappingMethod(JavaMethod method) {
        Optional<PutMapping> opt = method.tryGetAnnotationOfType(PutMapping.class);
        if (opt.isPresent()) {
            String[] methodURLs = getValuesOrPaths(opt.get().value(), opt.get().path());
            return concatClassUrlWithMethodUrl(method, methodURLs);
        }
        return new String[]{""};
    }

    private static String[] getURLFromDeleteMappingMethod(JavaMethod method) {
        Optional<DeleteMapping> opt = method.tryGetAnnotationOfType(DeleteMapping.class);
        if (opt.isPresent()) {
            String[] methodURLs = getValuesOrPaths(opt.get().value(), opt.get().path());
            return concatClassUrlWithMethodUrl(method, methodURLs);
        }
        return new String[]{""};
    }

    private static String[] getURLFromPatchMappingMethod(JavaMethod method) {
        Optional<PatchMapping> opt = method.tryGetAnnotationOfType(PatchMapping.class);
        if (opt.isPresent()) {
            String[] methodURLs = getValuesOrPaths(opt.get().value(), opt.get().path());
            return concatClassUrlWithMethodUrl(method, methodURLs);
        }
        return new String[]{""};
    }

    private static String[] concatClassUrlWithMethodUrl(JavaMethod method, String[] methodURLs) {
        String[] preFixURLs = new String[0];
        JavaClass owner = method.getOwner();
        Optional<RequestMapping> optionalRequestMapping = owner.tryGetAnnotationOfType(RequestMapping.class);
        if (optionalRequestMapping.isPresent()) {
            preFixURLs = getValuesOrPaths(optionalRequestMapping.get().value(), optionalRequestMapping.get().path());
        }
        List<String> urls = new ArrayList<>();
        if (preFixURLs.length > 0) {
            if(methodURLs != null){
                for (String methodURL : methodURLs) {
                    StringBuilder methodURLBuilder = new StringBuilder(methodURL);
                    for (String preFixURL : preFixURLs) {
                        if(methodURLBuilder.charAt(0) != '/'){
                            methodURLBuilder.insert(0, '/');
                        }
                        urls.add(preFixURL + methodURLBuilder);
                    }
                }
                String[] results = new String[urls.size()];
                results = urls.toArray(results);
                return results;
            }else{
                return preFixURLs;
            }
        } else {
            return methodURLs;
        }
    }

    private static String[] getValuesOrPaths(String[] value, String[] path) {
        if (value != null && value.length > 0) {
            return value;
        } else if (path != null && path.length > 0) {
            return path;
        } else {
            return null;
        }
    }

    private static boolean isPathParam(String s) {
        return s.matches("\\{[A-Za-z0-9_$]*}");
    }
}
